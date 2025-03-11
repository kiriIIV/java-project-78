package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema<Integer> {
    private Predicate<Integer> rangePredicate;

    public final NumberSchema required() {
        setRequired();
        predicates.add(number -> number != null);
        return this;
    }

    public final NumberSchema positive() {
        predicates.add(number -> number == null || number > 0);
        return this;
    }

    public final NumberSchema range(int left, int right) {
        if (left > right) {
            throw new IllegalArgumentException("Incorrect range!");
        }
        if (rangePredicate != null) {
            predicates.remove(rangePredicate);
        }
        rangePredicate = number -> number == null || (number >= left && number <= right);
        predicates.add(rangePredicate);
        return this;
    }
}
