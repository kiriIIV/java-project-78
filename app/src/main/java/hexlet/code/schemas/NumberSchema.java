package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {
    private boolean isPositive = false;
    private Integer leftRange = null;
    private Integer rightRange = null;

    public final NumberSchema required() {
        setRequired();
        return this;
    }

    public final NumberSchema positive() {
        this.isPositive = true;
        return this;
    }

    public final NumberSchema range(int left, int right) {
        if (left > right) {
            throw new IllegalArgumentException("Uncorrect range!");
        }
        this.leftRange = left;
        this.rightRange = right;
        return this;
    }

    @Override
    public final boolean isValid(Integer number) {
        if (checkRequired() && number == null) {
            return false;
        }
        if (number == null) {
            return true;
        }
        if (isPositive && number <= 0) {
            return false;
        }
        return leftRange == null || rightRange == null || (number >= leftRange && number <= rightRange);
    }
}
