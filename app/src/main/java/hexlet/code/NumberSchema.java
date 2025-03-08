package hexlet.code;

public class NumberSchema extends BaseSchema<Integer> {
    private boolean isPositive;
    private Integer leftRange;
    private Integer rightRange;

    public NumberSchema required() {
        setRequired();
        return this;
    }

    public NumberSchema positive() {
        this.isPositive = true;
        return this;
    }

    public NumberSchema range(int left, int right) {
        this.leftRange = left;
        this.rightRange = right;
        return this;
    }

    @Override
    public boolean isValid(Integer number) {
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
