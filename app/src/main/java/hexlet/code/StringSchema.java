package hexlet.code;

public class StringSchema {
    private boolean notNullAndEmpty;
    private int minLength;
    private String substring;

    public StringSchema required() {
        this.notNullAndEmpty = true;
        return this;
    }

    public StringSchema minLength(int length) {
        this.minLength = length;
        return this;
    }

    public StringSchema contains(String word) {
        this.substring = word;
        return this;
    }

    public boolean isValid(String sentence) {
        if (sentence == null) {
            return !notNullAndEmpty;
        }
        if (notNullAndEmpty && sentence.isEmpty()) {
            return false;
        }
        if (minLength > 0 && sentence.length() < minLength) {
            return false;
        }
        if (substring != null && !sentence.contains(substring)) {
            return false;
        }
        return true;
    }
}
