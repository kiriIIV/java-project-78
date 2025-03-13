package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapSchemaTest {
    private Validator v;
    private MapSchema schema;
    @BeforeEach
    public final void beforeEach() {
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
        schema.required().sizeof(1).sizeof(2);
        HashMap<String, String> data = new HashMap<>();
        data.put("key1", "value1");

        assertFalse(schema.isValid(data));

        data.put("key2", "value2");

        assertTrue(schema.isValid(data));
    }

    @Test
    public void test3() {
        schema.sizeof(5);

        assertTrue(schema.isValid(null));
    }

    @Test
    public void test4() {
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));
        schema.shape(schemas);
        Map<String, String> human = new HashMap<>();
        human.put("firstName", "John");
        human.put("lastName", "Smith");

        assertTrue(schema.isValid(human));
    }

    @Test
    public void test5() {
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));
        schema.shape(schemas);
        Map<String, String> human = new HashMap<>();
        human.put("firstName", "John");
        human.put("lastName", null);

        assertFalse(schema.isValid(human));
    }

    @Test
    public void test6() {
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2).contains("123"));
        schema.shape(schemas);
        Map<String, String> human = new HashMap<>();
        human.put("firstName", "Anna");
        human.put("lastName", "Bob");

        assertFalse(schema.isValid(human));
    }

    @Test
    public void test7() {
        Map<Integer, BaseSchema<Integer>> schemas = new HashMap<>();
        schemas.put(1, v.number().positive().required());
        schemas.put(2, v.number().required());
        schema.shape(schemas);
        Map<Integer, Integer> numbers = new HashMap<>();
        numbers.put(1, 5);
        numbers.put(2, -5);

        assertTrue(schema.isValid(numbers));
    }

    @Test
    public void test8() {
        Map<Integer, BaseSchema<Integer>> schemas = new HashMap<>();
        schemas.put(1, v.number().positive().required());
        schemas.put(2, v.number().required());
        schema.shape(schemas);
        Map<Integer, Integer> numbers = new HashMap<>();
        numbers.put(1, 5);
        numbers.put(2, null);

        assertFalse(schema.isValid(numbers));
    }

    @Test
    public void test9() {
        Map<Integer, BaseSchema<Integer>> schemas = new HashMap<>();
        schemas.put(1, v.number().positive().required());
        schemas.put(2, v.number().required());
        schema.shape(schemas);
        Map<Integer, Integer> numbers = new HashMap<>();
        numbers.put(1, 5);

        assertFalse(schema.isValid(numbers));
    }

    @Test
    public void test10() {
        Map<Integer, BaseSchema<Integer>> schemas = new HashMap<>();
        schemas.put(1, v.number().positive().required().range(1, 5));
        schemas.put(2, v.number().required());
        schema.shape(schemas);
        Map<Integer, Integer> numbers = new HashMap<>();
        numbers.put(1, 5);
        numbers.put(2, 56);

        assertTrue(schema.isValid(numbers));
    }

    @Test
    public void test11() {
        Map<Integer, BaseSchema<Integer>> schemas = new HashMap<>();
        schemas.put(1, v.number().positive().required().range(1, 5));
        schemas.put(2, v.number().required());
        schema.shape(schemas);
        Map<Integer, Integer> numbers = new HashMap<>();
        numbers.put(1, 5);
        numbers.put(3, 56);

        assertFalse(schema.isValid(numbers));
    }

    @Test
    public void test12() {
        Map<Integer, BaseSchema<Map<?, ?>>> schemas = new HashMap<>();
        schemas.put(1, v.map().required().sizeof(2));
        schemas.put(2, v.map().required());
        schema.shape(schemas);
        Map<Integer, Map<?, ?>> map = new HashMap<>();
        map.put(1, Map.of("12", "34", "56", "78"));
        map.put(2, Map.of());

        assertTrue(schema.isValid(map));
    }

    @Test
    public void test13() {
        Map<Integer, BaseSchema<Map<?, ?>>> schemas = new HashMap<>();
        schemas.put(1, v.map().required().sizeof(2));
        schemas.put(2, v.map().required());
        schema.shape(schemas);
        Map<Integer, Map<?, ?>> map = new HashMap<>();
        map.put(1, Map.of("12", "34", "56", "78"));
        map.put(2, null);

        assertFalse(schema.isValid(map));
    }

    @Test
    public void test14() {
        assertThrows(IllegalArgumentException.class, () -> schema.sizeof(-5));
    }
}
