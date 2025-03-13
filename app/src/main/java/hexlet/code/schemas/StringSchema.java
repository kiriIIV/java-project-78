package hexlet.code.schemas;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema<String> {
    private Predicate<String> minLengthPredicate;

    @Override
    public final StringSchema required() {
        super.required();
        addPredicate(str -> !str.isEmpty());
        return this;
    }

    public final StringSchema minLength(int length) {
        if (minLengthPredicate != null) {
            deletePredicate(minLengthPredicate);
        }
        minLengthPredicate = str -> str == null || str.length() >= length;
        addPredicate(minLengthPredicate);
        return this;
    }

    public final StringSchema contains(String word) {
        addPredicate(sentence -> sentence == null || sentence.contains(word));
        return this;
    }
}
