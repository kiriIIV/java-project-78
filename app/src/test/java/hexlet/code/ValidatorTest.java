package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {

    @Test
    public void testStringSchema() {
        var v = new Validator();

        assertTrue(v.string() instanceof StringSchema);
    }

    @Test
    public void testNumberSchema() {
        var v = new Validator();

        assertTrue(v.number() instanceof NumberSchema);
    }
}
