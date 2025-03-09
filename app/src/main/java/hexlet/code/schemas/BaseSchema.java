package hexlet.code.schemas;

public abstract class BaseSchema<T> {
    private boolean required = false;

    public final void setRequired() {
        this.required = true;
    }

    protected final boolean checkRequired() {
        return required;
    }

    public abstract boolean isValid(T someValue);
}
