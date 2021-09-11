package pureRestAssuredSolution;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.config.JsonPathConfig;
import org.junit.Before;

import java.io.IOException;

public class TestConfig {

    public static String users;

    @Before
    public void setup() throws IOException {
        RestAssured.baseURI = "https://reqres.in";
        JsonPath.config = new JsonPathConfig("UTF-8");
        RestAssured.defaultParser = Parser.JSON;

        users = "/api/users";
    }
}
