package specs;

import io.restassured.http.ContentType;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class reqresTest extends TestConfig {

    @Test
    public void firstTest(){
        given()
            .contentType(ContentType.JSON)
        .when()
            .get(baseURL + "api/users?page=2")
        .then()
            .statusCode(200);
    }

}

