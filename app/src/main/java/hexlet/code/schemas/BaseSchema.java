package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema<T> {
    protected List<Predicate<T>> predicates = new ArrayList<>();

    public final boolean isValid(T someValue) {
        Predicate<T> result = value -> true;
        for (var pr : predicates) {
            result = result.and(pr);
        }

        return result.test(someValue);
    }
}
