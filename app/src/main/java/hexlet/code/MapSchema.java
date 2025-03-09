package hexlet.code;

import java.util.HashMap;
import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    private Integer sizeOfMap = null;
    private Map<Object, BaseSchema<?>> schemas;

    public MapSchema required() {
        setRequired();
        return this;
    }

    public MapSchema sizeof(int size) {
        this.sizeOfMap = size;
        return this;
    }

    public <K, T> MapSchema shape(Map<K, BaseSchema<T>> newSchemas) {
        this.schemas = new HashMap<>();
        for (Map.Entry<K, BaseSchema<T>> entry : newSchemas.entrySet()) {
            this.schemas.put(entry.getKey(), entry.getValue());
        }
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
    public boolean isValid(Map<?, ?> someValue) {
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
