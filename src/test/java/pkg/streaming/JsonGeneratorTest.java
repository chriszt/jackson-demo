package pkg.streaming;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import org.junit.Test;
import pkg.bean.User;
import pkg.tree.UserTreeNode;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

public class JsonGeneratorTest {

    @Test
    public void test1() throws IOException {
        JsonFactory factory = new JsonFactory();
        JsonGenerator generator = null;

        try {
            generator = factory.createGenerator(System.out, JsonEncoding.UTF8);

            generator.writeStartObject();
            generator.writeStringField("name", "zhangsan");
            generator.writeNumberField("age", 18);
            generator.writeEndObject();
        } finally {
            generator.close();
        }
    }

    @Test
    public void test2() throws IOException {
        JsonFactory factory = new JsonFactory();

        // try-with-resources
        try (JsonGenerator generator = factory.createGenerator(System.out, JsonEncoding.UTF8)) {
            generator.writeStartObject();

            generator.writeStringField("name", "zhangsan");
            generator.writeNumberField("age", 18);

            generator.writeEndObject();
        }
    }

    @Test
    public void test3() throws IOException {
        JsonFactory factory = new JsonFactory();

        // try-with-resources
        try (JsonGenerator generator = factory.createGenerator(System.out, JsonEncoding.UTF8)) {
            generator.writeStartObject();

//            generator.writeFieldId(10L);
            generator.writeFieldName("name");

            generator.writeEndObject();
        }
    }

    @Test
    public void test4() throws IOException {
        JsonFactory factory = new JsonFactory();

        // try-with-resources
        try (JsonGenerator generator = factory.createGenerator(System.out, JsonEncoding.UTF8)) {
            generator.writeStartObject();

            generator.writeFieldName("name");
            generator.writeString("zhangsan");

            generator.writeFieldName("age");
            generator.writeNumber(18);

            generator.writeEndObject();
        }
    }

    @Test
    public void test5() throws IOException {
        JsonFactory factory = new JsonFactory();

        // try-with-resources
        try (JsonGenerator generator = factory.createGenerator(System.out, JsonEncoding.UTF8)) {
            generator.writeStartObject();

            generator.writeFieldName("name");
            generator.writeString("zhangsan");

            generator.writeFieldName("detail");
            generator.writeStartObject();
            generator.writeFieldName("age");
            generator.writeNumber(18);
            generator.writeFieldName("countr");
            generator.writeString("zh-cn");
            generator.writeEndObject();

            generator.writeEndObject();
        }
    }

    @Test
    public void test6() throws IOException {
        JsonFactory factory = new JsonFactory();

        // try-with-resources
        try (JsonGenerator generator = factory.createGenerator(System.out, JsonEncoding.UTF8)) {
            generator.writeStartObject();

            generator.writeFieldName("name");
            generator.writeString("zhangsan");

            generator.writeFieldName("objs");
            generator.writeStartArray();
            generator.writeString("boy");
            generator.writeStartObject();
            generator.writeStringField("nick", "abc");
            generator.writeEndObject();
            generator.writeEndArray();

            generator.writeEndObject();
        }
    }

    @Test
    public void test7() throws IOException {
        JsonFactory factory = new JsonFactory();

        // try-with-resources
        try (JsonGenerator generator = factory.createGenerator(System.out, JsonEncoding.UTF8)) {
            generator.writeStartObject();

            generator.writeFieldName("name");
            generator.writeString("zhangsan");

            generator.writeFieldName("values");
            generator.writeArray(new int[]{1, 2, 3, 4, 5, 6}, 2, 3);

            generator.writeEndObject();
        }
    }

    @Test
    public void test8() throws IOException {
        JsonFactory factory = new JsonFactory();

        // try-with-resources
        try (JsonGenerator generator = factory.createGenerator(System.out, JsonEncoding.UTF8)) {
            generator.writeStartObject();

            generator.writeFieldName("success");
            generator.writeBoolean(true);
            generator.writeFieldName("salary");
            generator.writeNull();

            generator.writeEndObject();
        }
    }

    @Test
    public void test9() throws IOException {
        JsonFactory factory = new JsonFactory();

        // try-with-resources
        try (JsonGenerator generator = factory.createGenerator(System.out, JsonEncoding.UTF8)) {
            generator.writeStartObject();

            generator.writeStringField("name", "zhangsan");
            generator.writeBooleanField("success", true);
            generator.writeNullField("nick");
            generator.writeObjectFieldStart("objs");
            generator.writeEndObject();
            generator.writeArrayFieldStart("arrs");

            generator.writeEndObject();
        }
    }

    @Test
    public void test10() throws IOException {
        JsonFactory factory = new JsonFactory();

        // try-with-resources
        try (JsonGenerator generator = factory.createGenerator(System.out, JsonEncoding.UTF8)) {
            // {'name':'zhangsan'}
            generator.writeRaw("{'name':'zhangsan'}");

            // "{'name':'zhangsan'}"
            generator.writeString("{'name':'zhangsan'}");
        }
    }

    @Test
    public void test11() throws IOException {
        JsonFactory factory = new JsonFactory();

        // try-with-resources
        try (JsonGenerator generator = factory.createGenerator(System.out, JsonEncoding.UTF8)) {
            // null
            generator.writeEmbeddedObject(null);

            // "dGhpcyBpcyBhIHRlc3Q="
            generator.writeEmbeddedObject("this is a test".getBytes(StandardCharsets.UTF_8));

            generator.writeEmbeddedObject(new User());
        }
    }

    @Test
    public void test12() throws IOException {
        JsonFactory factory = new JsonFactory();

        // try-with-resources
        try (JsonGenerator generator = factory.createGenerator(System.out, JsonEncoding.UTF8)) {
            generator.setCodec(new UserObjectCodec());
            generator.writeObject(new User());
        }
    }

    @Test
    public void test13() throws IOException {
        JsonFactory factory = new JsonFactory();

        // try-with-resources
        try (JsonGenerator generator = factory.createGenerator(System.out, JsonEncoding.UTF8)) {
            generator.setCodec(new UserObjectCodec());
            generator.writeObject(new UserTreeNode(new User()));
        }
    }

    @Test
    public void test14() throws IOException {
        JsonFactory factory = new JsonFactory();

        // try-with-resources
        try (JsonGenerator generator = factory.createGenerator(System.out, JsonEncoding.UTF8)) {
//            generator.disable(JsonGenerator.Feature.QUOTE_FIELD_NAMES);

            generator.writeStartObject();
            generator.writeStringField("name", "zhangsan");
            generator.writeEndObject();
        }
    }

    @Test
    public void test15() throws IOException {
        JsonFactory factory = new JsonFactory();

        // try-with-resources
        try (JsonGenerator generator = factory.createGenerator(System.out, JsonEncoding.UTF8)) {
            generator.disable(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS);

            generator.writeNumber(0.9);
            generator.writeNumber(1.9);

            generator.writeNumber(Float.NaN);
            generator.writeNumber(Float.NEGATIVE_INFINITY);
            generator.writeNumber(Float.POSITIVE_INFINITY);
        }
    }

    @Test
    public void test16() throws IOException {
        JsonFactory factory = new JsonFactory();

        // try-with-resources
        try (JsonGenerator generator = factory.createGenerator(System.out, JsonEncoding.UTF8)) {
            generator.enable(JsonGenerator.Feature.ESCAPE_NON_ASCII);

            generator.writeString("A哥");
        }
    }

    @Test
    public void test17() throws IOException {
        JsonFactory factory = new JsonFactory();

        // try-with-resources
        try (JsonGenerator generator = factory.createGenerator(System.out, JsonEncoding.UTF8)) {
//            generator.enable(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS);

            generator.writeNumber(Long.MAX_VALUE);
        }
    }

    @Test
    public void test18() throws IOException {
        JsonFactory factory = new JsonFactory();

        // try-with-resources
        try (JsonGenerator generator = factory.createGenerator(System.out, JsonEncoding.UTF8)) {
            generator.enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN);

            BigDecimal bd1 = new BigDecimal(1.0);
            BigDecimal bd2 = new BigDecimal("1.0");
            BigDecimal bd3 = new BigDecimal("1E11");
            generator.writeNumber(bd1);
            generator.writeNumber(bd2);
            generator.writeNumber(bd3);
        }
    }

    @Test
    public void test19() throws IOException {
        JsonFactory factory = new JsonFactory();

        // try-with-resources
        try (JsonGenerator generator = factory.createGenerator(System.out, JsonEncoding.UTF8)) {
//            generator.enable(JsonGenerator.Feature.STRICT_DUPLICATE_DETECTION);

            generator.writeStartObject();
            generator.writeStringField("name", "zhangsan");
            generator.writeStringField("name", "lisi");
            generator.writeEndObject();
        }
    }

    @Test
    public void test20() throws IOException {
        JsonFactory factory = new JsonFactory();

        // try-with-resources
        try (JsonGenerator generator = factory.createGenerator(System.out, JsonEncoding.UTF8)) {
            generator.useDefaultPrettyPrinter();

            generator.writeStartObject();
            generator.writeStringField("name", "zhangsan");
            generator.writeNumberField("age", 18);
            generator.writeEndObject();
        }
    }

}
