package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema<T> {
    protected List<Predicate<T>> predicates = new ArrayList<>();
    private boolean isRequired = false;

    public BaseSchema<T> required() {
        isRequired = true;
        predicates.add(value -> value != null);
        return this;
    }

    public final void addPredicate(Predicate<T> pr) {
        predicates.add(pr);
    }

    public final void deletePredicate(Predicate<T> pr) {
        predicates.remove(pr);
    }

    public final boolean isValid(T someValue) {
        if (isRequired && someValue == null) {
            return false;
        }
        Predicate<T> result = value -> true;
        for (var pr : predicates) {
            result = result.and(pr);
        }

        return result.test(someValue);
    }
}
