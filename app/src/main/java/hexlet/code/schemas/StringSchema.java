package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {
    private Integer minLength = null;
    private String substring = null;

    public final StringSchema required() {
        setRequired();
        return this;
    }

    public final StringSchema minLength(int length) {
        this.minLength = length;
        return this;
    }

    public final StringSchema contains(String word) {
        this.substring = word;
        return this;
    }

    @Override
    public final boolean isValid(String sentence) {
        if (checkRequired() && (sentence == null || sentence.isEmpty())) {
            return false;
        }
        if (sentence == null) {
            return true;
        }
        if (minLength != null && sentence.length() < minLength) {
            return false;
        }
        return substring == null || sentence.contains(substring);
    }
}
