package cucumberSolution.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class ReqresSteps {

    @When("I perform GET operation for {string}")
    public void iPerformGETOperationFor(String url, int statusCode) {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://reqres.in/api/users/2")
                .then().statusCode(statusCode);
    }

    @Then("Assert status code is {int}")
    public void assertStatusCodeIs(int statusCode) {

    }
}
