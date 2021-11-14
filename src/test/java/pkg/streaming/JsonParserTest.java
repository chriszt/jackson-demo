package pkg.streaming;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import org.junit.Test;
import pkg.bean.User;

import java.io.IOException;

public class JsonParserTest {

    @Test
    public void test1() throws IOException {
        String jsonStr = "{\"name\":\"lisi\",\"age\":20}";
        User user = new User();

        JsonFactory factory = new JsonFactory();
        try (JsonParser jsonParser = factory.createParser(jsonStr)) {
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = jsonParser.getCurrentName();
                if ("name".equals(fieldName)) {
                    jsonParser.nextToken();
                    user.setName(jsonParser.getText());
                } else if ("age".equals(fieldName)) {
                    jsonParser.nextToken();
                    user.setAge(jsonParser.getIntValue());
                }
            }

            System.out.println(user);
        }
    }

    @Test
    public void test2() throws IOException {
        String jsonStr = "{\"name\":\"lisi\",\"age\":20,\"pickName\":null}";
        System.out.println(jsonStr);

        JsonFactory factory = new JsonFactory();
        try (JsonParser jsonParser = factory.createParser(jsonStr)) {
            while (true) {
                JsonToken token = jsonParser.nextToken();
                System.out.println(token + "-> value: " + jsonParser.getValueAsString());

                if (token == JsonToken.END_OBJECT) {
                    break;
                }
            }
        }

    }

    @Test
    public void test3() throws IOException {
        String jsonStr = "{\"name\":\"lisi\",\"age\":20,\"pickName\":null}";

        JsonFactory factory = new JsonFactory();
        try (JsonParser jsonParser = factory.createParser(jsonStr)) {
            jsonParser.setCodec(new UserObjectCodec());

            User user = jsonParser.readValueAs(User.class);
            System.out.println(user);
        }
    }

    @Test
    public void test4() throws IOException {
        String jsonStr = "{\n" +
                "\t\"name\" : \"YourBarman\", // 名字\n" +
                "\t\"age\" : 18 // 年龄\n" +
                "}";
        System.out.println(jsonStr);

        JsonFactory factory = new JsonFactory();
        try (JsonParser jsonParser = factory.createParser(jsonStr)) {
            jsonParser.enable(JsonParser.Feature.ALLOW_COMMENTS);
//            jsonParser.enable(JsonParser.Feature.ALLOW_YAML_COMMENTS);

            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = jsonParser.getCurrentName();
                if ("name".equals(fieldName)) {
                    jsonParser.nextToken();
                    System.out.println(jsonParser.getText());
                } else if ("age".equals(fieldName)) {
                    jsonParser.nextToken();
                    System.out.println(jsonParser.getIntValue());
                }
            }
        }
    }

}
