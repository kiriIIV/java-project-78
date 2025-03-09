package hexlet.code.schemas;

public abstract class BaseSchema<T> {
    private boolean required = false;

    public void setRequired() {
        this.required = true;
    }

    protected boolean checkRequired() {
        return required;
    }

    public abstract boolean isValid(T someValue);
}
