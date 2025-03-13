package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema<Integer> {
    private Predicate<Integer> rangePredicate;

    @Override
    public final NumberSchema required() {
        super.required();
        return this;
    }

    public final NumberSchema positive() {
        addPredicate(number -> number == null || number > 0);
        return this;
    }

    public final NumberSchema range(int left, int right) {
        if (left > right) {
            throw new IllegalArgumentException("Incorrect range!");
        }
        if (rangePredicate != null) {
            deletePredicate(rangePredicate);
        }
        rangePredicate = number -> number == null || (number >= left && number <= right);
        addPredicate(rangePredicate);
        return this;
    }
}
