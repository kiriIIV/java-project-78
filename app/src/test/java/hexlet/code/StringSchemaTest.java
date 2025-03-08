package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringSchemaTest {
    private Validator v;
    private StringSchema schema;
    @BeforeEach
    public void beforeEach() {
        v = new Validator();
        schema = v.string();
    }

    @Test
    public void test1() {
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid("12345"));

        schema.required();

        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid("12345"));
    }

    @Test
    public void test2() {
        assertTrue(schema.contains("wh").isValid("what does the fox say"));
        assertTrue(schema.contains("what").isValid("what does the fox say"));
        assertFalse(schema.contains("123").isValid("what does the fox say"));
    }

    @Test
    public void test3() {
        schema.contains("wh");
        StringSchema schema1 = v.string();
        assertTrue(schema1.minLength(10).minLength(4).isValid("House"));
    }

    @Test
    public void test4() {
        schema.required().minLength(5);
        assertFalse(schema.isValid("cat"));
    }

    @Test
    public void test5() {
        schema.minLength(5).contains("123");
        assertTrue(schema.isValid(null));
    }
}
