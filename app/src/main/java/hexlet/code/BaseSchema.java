package hexlet.code;

public abstract class BaseSchema<T> {
    private boolean required;

    public void setRequired() {
        this.required = true;
    }

    protected boolean checkRequired() {
        return required;
    }

    public abstract boolean isValid(T someValue);
}
