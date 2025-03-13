package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    private Map<Object, BaseSchema<?>> schemas;
    private Predicate<Map<?, ?>> predicateOfSize;

    @Override
    public final MapSchema required() {
        super.required();
        return this;
    }

    public final MapSchema sizeof(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Incorrect size");
        }
        if (predicateOfSize != null) {
            deletePredicate(predicateOfSize);
        }
        predicateOfSize = map -> map == null || map.size() == size;
        addPredicate(predicateOfSize);
        return this;
    }

    public final <K, T> MapSchema shape(Map<K, BaseSchema<T>> newSchemas) {
        this.schemas = new HashMap<>();
        this.schemas.putAll(newSchemas);
        addPredicate(map -> {
            if (schemas == null) {
                return true;
            }
            for (var key : schemas.keySet()) {
                if (!map.containsKey(key)) {
                    return false;
                }
                BaseSchema<Object> schema = (BaseSchema<Object>) schemas.get(key);
                if (schema == null || !schema.isValid(map.get(key))) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }
}
