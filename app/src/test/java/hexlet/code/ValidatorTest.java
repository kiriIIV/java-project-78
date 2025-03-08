package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {

    @Test
    public void testStringSchema() {
        var v = new Validator();

        assertTrue(v.string() instanceof StringSchema);
    }
}
