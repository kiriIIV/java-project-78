package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema<T> {
    protected List<Predicate<T>> predicates = new ArrayList<>();
    private boolean required = false;

    public final void setRequired() {
        this.required = true;
    }

    protected final boolean checkRequired() {
        return required;
    }


    public boolean isValid(T someValue) {
        Predicate<T> result = value -> true;
        for (var pr : predicates) {
            result = result.and(pr);
        }

        return result.test(someValue);
    }
}
