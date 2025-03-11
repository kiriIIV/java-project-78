package hexlet.code.schemas;

import java.util.function.Predicate;

public class StringSchema extends BaseSchema<String> {
    private Predicate<String> minLengthPredicate;

    public final StringSchema required() {
        setRequired();
        predicates.add(sentence -> sentence != null && !sentence.isEmpty());
        return this;
    }

    public final StringSchema minLength(int length) {
        if (minLengthPredicate != null) {
            predicates.remove(minLengthPredicate);
        }
        minLengthPredicate = str -> str == null || str.length() >= length;
        predicates.add(minLengthPredicate);
        return this;
    }

    public final StringSchema contains(String word) {
        predicates.add(sentence -> sentence == null || sentence.contains(word));
        return this;
    }
}
