package utilities;

import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class ApiUtils {

    public static Response get(String url){
        return when().get(url).then().using().extract().response();
    }

    public static Response post(String url, HashMap<String, String> userJson) {
        return given().body(userJson).post(url).then().using().extract().response();
    }

    public static ResponseOptions<Response> putWithPathParam(String url, String userId, HashMap<String, String> userJson) {
        return given().body(userJson).put(url.concat("/").concat(userId)).then().using().extract().response();
    }

    public static ResponseOptions<Response> getWithQueryParam(String url, String key, String value) {
        return given().params(key, value).get(url).then().using().extract().response();
    }

    public static ResponseOptions<Response> deleteWithPathParam(String url, String pathParam) {
        return when().delete(url.concat("/").concat(pathParam)).then().using().extract().response();
    }
}
