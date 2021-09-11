package pureRestAssuredSolution;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class GetUserTest extends TestConfig {

    @Test
    public void getListOfUsers(){
        given()
            .param("page", 1)
        .when()
            .get(users)
        .then()
            .statusCode(200)
        .and()
             .log()
             .all();
    }

    @Test
    public void getUserById(){
        String userId = "1";
        when()
            .get(users.concat("/").concat(userId))
        .then()
            .statusCode(200)
        .and()
            .log()
            .all();
    }
}

