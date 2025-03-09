package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapSchemaTest {
    private Validator v;
    private MapSchema schema;
    @BeforeEach
    public void beforeEach() {
        v = new Validator();
        schema = v.map();
    }

    @Test
    public void test1() {
        assertTrue(schema.isValid(null));

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(new HashMap<>()));
    }

    @Test
    public void test2() {
        schema.required();
        HashMap<String, String> data = new HashMap<>();
        data.put("key1", "value1");

        assertTrue(schema.isValid(data));

        schema.sizeof(2);

        assertFalse(schema.isValid(data));

        data.put("key2", "value2");

        assertTrue(schema.isValid(data));
    }

    @Test
    public void test3() {
        schema.sizeof(5);

        assertTrue(schema.isValid(null));
    }
}
