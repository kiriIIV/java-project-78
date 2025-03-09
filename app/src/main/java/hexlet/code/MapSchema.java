package hexlet.code;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>> {
    private Integer sizeOfMap = null;

    public MapSchema required() {
        setRequired();
        return this;
    }

    public MapSchema sizeof(int size) {
        this.sizeOfMap = size;
        return this;
    }

    @Override
    public boolean isValid(Map<?, ?> someValue) {
        if (checkRequired() && someValue == null) {
            return false;
        }
        if (someValue == null) {
            return true;
        }
        return sizeOfMap == null || someValue.size() == sizeOfMap;
    }
}
