package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberSchemaTest {
    private Validator v;
    private NumberSchema schema;
    @BeforeEach
    public void beforeEach() {
        v = new Validator();
        schema = v.number();
    }

    @Test
    public void test1() {
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(null));
        assertTrue(schema.positive().isValid(null));

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(10));
    }

    @Test
    public void test2() {
        schema.positive();
        assertFalse(schema.isValid(-5));
        assertTrue(schema.isValid(12));
        assertFalse(schema.isValid(0));
    }

    @Test
    public void test3() {
        schema.range(5, 10);
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }

    @Test
    public void test4() {
        schema.positive().range(4, 9);
        assertTrue(schema.isValid(null));
    }

    @Test
    public void test5() {
        schema.positive().range(-10, -5);
        assertFalse(schema.isValid(-7));
    }
}
