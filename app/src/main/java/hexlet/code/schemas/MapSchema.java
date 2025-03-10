package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    private Integer sizeOfMap = null;
    private Map<Object, BaseSchema<?>> schemas;

    public final MapSchema required() {
        setRequired();
        return this;
    }

    public final MapSchema sizeof(int size) {
        this.sizeOfMap = size;
        return this;
    }

    public final <K, T> MapSchema shape(Map<K, BaseSchema<T>> newSchemas) {
        this.schemas = new HashMap<>();
        this.schemas.putAll(newSchemas);
        return this;
    }

    private boolean checkSchemas(Map<?, ?> validMap) {
        if (schemas == null) {
            return true;
        }
        if (schemas.size() != validMap.size()) {
            return false;
        }
        for (var key : validMap.keySet()) {
            if (!schemas.containsKey(key)) {
                return false;
            }
            BaseSchema<Object> schema = (BaseSchema<Object>) schemas.get(key);
            if (schema == null || !schema.isValid(validMap.get(key))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public final boolean isValid(Map<?, ?> someValue) {
        if (checkRequired() && someValue == null) {
            return false;
        }
        if (someValue == null) {
            return true;
        }
        if (sizeOfMap != null && someValue.size() != sizeOfMap) {
            return false;
        }
        return checkSchemas(someValue);
    }
}
