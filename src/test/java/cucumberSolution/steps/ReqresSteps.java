package cucumberSolution.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.internal.com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import org.junit.Assert;
import utilities.ApiUtils;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static io.restassured.path.json.JsonPath.from;

public class ReqresSteps{

    private static ResponseOptions<Response> response;
    private static String userId;

    @When("Get user with id {string} by calling {string}")
    public void getUserWithIdByCalling(String id, String url){
        response = ApiUtils.get(url.concat("/").concat(id));
    }

    @Then("Assert status code is {int}")
    public void assertStatusCodeIs(int statusCode) {
        Assert.assertEquals(statusCode, response.statusCode());
    }

    @Given("Setup base url and path")
    public void setupBaseUrlAndPath() {
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }

    @When("Create user with name {string} and job {string} by calling {string}")
    public void createUserWithNameAndJobByCalling(String testUserName, String testJob, String url) {
        HashMap<String, String> userJson = new HashMap<>();
        userJson.put("name", testUserName);
        userJson.put("job", testJob);

        response = ApiUtils.post(url, userJson);
        userId =  response.getBody().jsonPath().get("id");
    }

    @And("Assert {string} and {string} are returned in body of the response")
    public void assertAndAreReturnedInBodyOfTheResponse(String userName, String userJob) {
        Assert.assertEquals(response.getBody().jsonPath().get("name"), userName);
        Assert.assertEquals(response.getBody().jsonPath().get("job"), userJob);
    }

    @When("Hit {string} with the returned userid")
    public void hitWithTheReturnedUserid(String url) {
        response = ApiUtils.get(url.concat("/").concat(userId));
    }

    @When("Register user with email {string} and password {string} by calling {string}")
    public void registerUserWithEmailAndPasswordByCalling(String email, String password, String url) {
        HashMap<String, String> registerUserJson = new HashMap<>();
        registerUserJson.put("email", email);
        registerUserJson.put("password", password);

        response = ApiUtils.post(url, registerUserJson);
        userId =  response.getBody().jsonPath().get("id").toString();
    }

    @And("Assert {string} and {string} fields are not null")
    public void assertAndFieldsAreNotNull(String idField, String tokenField) {
        Assert.assertNotNull(response.getBody().jsonPath().get(idField));
        Assert.assertNotNull(response.getBody().jsonPath().get(tokenField));
    }


    @When("Register user with email {string} and no password by calling {string}")
    public void registerUserWithEmailAndNoPasswordByCalling(String email, String url) {
        HashMap<String, String> registerUserJson = new HashMap<>();
        registerUserJson.put("email", email);

        response = ApiUtils.post(url, registerUserJson);
    }

    @And("Assert {string} {string} is returned")
    public void assertIsReturned(String key, String value) {
        Assert.assertEquals(value, response.getBody().jsonPath().get(key).toString());
    }

    @When("Login with email {string} and password {string} by calling {string}")
    public void loginWithEmailAndPasswordByCalling(String email, String password, String url) {
        HashMap<String, String> loginUserJson = new HashMap<>();
        loginUserJson.put("email", email);
        loginUserJson.put("password", password);

        response = ApiUtils.post(url, loginUserJson);
    }

    @When("Login with email {string} and no password by calling {string}")
    public void loginWithEmailAndNoPasswordByCalling(String email, String url) {
        HashMap<String, String> registerUserJson = new HashMap<>();
        registerUserJson.put("email", email);

        response = ApiUtils.post(url, registerUserJson);
    }

    @When("Update userId {string} with name {string} and job {string} by calling {string}")
    public void updateUserIdWithNameAndJobByCalling(String userId, String name, String job, String url) {
        HashMap<String, String> userJson = new HashMap<>();
        userJson.put("name", name);
        userJson.put("job", job);

        response = ApiUtils.putWithPathParam(url, userId, userJson);
    }

    @When("Get page {string} by calling {string}")
    public void getPageByCalling(String pageNumber, String url) {
        response = ApiUtils.getWithQueryParam(url,"page", pageNumber);
    }

    @And("Assert more than {int} id is returned")
    public void assertMoreThanIdIsReturned(int size) {
        String responseBodyString = response.getBody().asString();

        assert from(responseBodyString).getList("findAll { it.data.id}").size() > size;
    }

    @When("Delete user with id {string} by calling {string}")
    public void deleteUserWithIdByCalling(String id, String url) {
        response = ApiUtils.deleteWithPathParam(url, id);
    }

    @When("Get list of user with delay {string} seconds by calling {string}")
    public void getListOfUserWithDelaySecondsByCalling(String seconds, String url) {
        response = ApiUtils.getWithQueryParam(url,"delay", seconds);
    }

    @And("Response time is slower than {int} seconds")
    public void responseTimeIsSlowerThanSeconds(int responseTime) {
        assert response.getTimeIn(TimeUnit.SECONDS) < responseTime;
    }
}
