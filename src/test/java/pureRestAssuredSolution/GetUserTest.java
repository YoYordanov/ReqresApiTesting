package pureRestAssuredSolution;
import org.junit.Test;
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

