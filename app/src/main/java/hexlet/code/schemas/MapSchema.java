package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    private Map<Object, BaseSchema<?>> schemas;
    private Predicate<Map<?, ?>> predicateOfSize;

    public final MapSchema required() {
        setRequired();
        predicates.add(map -> map != null);
        return this;
    }

    public final MapSchema sizeof(int size) {
        if (predicateOfSize != null) {
            predicates.remove(predicateOfSize);
        }
        predicateOfSize = map -> map == null || map.size() == size;
        predicates.add(predicateOfSize);
        return this;
    }

    public final <K, T> MapSchema shape(Map<K, BaseSchema<T>> newSchemas) {
        this.schemas = new HashMap<>();
        this.schemas.putAll(newSchemas);
        predicates.add(map -> {
            if (schemas == null) {
                return true;
            }
            for (var key : schemas.keySet()) {
                if (!map.containsKey(key)) {
                    return false; // Если ключа нет, сразу false
                }
                BaseSchema<Object> schema = (BaseSchema<Object>) schemas.get(key);
                if (schema == null || !schema.isValid(map.get(key))) {
                    return false; // Если значение не проходит валидацию
                }
            }
            return true;
        });
        return this;
    }
}
