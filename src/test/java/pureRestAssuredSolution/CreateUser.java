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
import static org.hamcrest.Matchers.equalTo;

public class CreateUser extends TestConfig{

    /*
    Example for test driven development using rest assured BDD stile.

    Test driven because the the server is most probably deleting the created user and the test cannot proceed after that.
     */
    @Test
    public void createUserTest() throws IOException {

        String userJson = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/src/test/resources/testUser.json")));

        // Create new user
        Response response = (Response) given()
            .contentType(ContentType.JSON)
            .body(userJson)
        .when()
            .post(users)
        .then()
            .statusCode(201)
            .log()
            .body()
            .extract();

        JsonPath responseJsonPath = new JsonPath(response.getBody().asString());
        String userId = responseJsonPath.getString("id");

        JsonPath requestJsonPath = new JsonPath(userJson);
        String expectedName = requestJsonPath.getString("name");
        String expectedJob = requestJsonPath.getString("job");

        //Get the created user
        given()
            .accept(ContentType.JSON)
        .when()
            .get(users.concat("/").concat(userId))
        .then()
            .log()
            .body()
        .and()
            .body("name", equalTo(expectedName))
            .body("job", equalTo(expectedJob));

        //Delete the created user
        given()
            .accept(ContentType.JSON)
        .when()
            .delete(users.concat("/").concat(userId))
        .then()
            .statusCode(204);


        //Confirm user is deleted
        when()
            .get(users.concat("/").concat(userId))
        .then()
            .statusCode(404);
    }

}
