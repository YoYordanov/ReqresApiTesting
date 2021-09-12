package utilities;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

public class ApiUtil {

    public static RequestSpecification Request;

    public ApiUtil() {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri("https://reqres.in");
        builder.setContentType(ContentType.JSON);
        var requestSpec = builder.build();
        Request = RestAssured.given().spec(requestSpec);
    }

    public ResponseOptions<Response> get(String url) throws URISyntaxException {
        Request.get(new URI(url));
        return null;
    }

    public ResponseOptions<Response> post(String url, Map<String, String> body) throws URISyntaxException {
        Request.body(body);
        return Request.post(new URI(url));
    }

    public ResponseOptions<Response> putWithPathParam(String url, String pathParam, Map<String, String> body) throws URISyntaxException {
        Request.body(body);
        return Request.put(new URI(url.concat("/").concat(pathParam)));
    }

    public ResponseOptions<Response> getWithQueryParam(String url, String key, String value) throws URISyntaxException {
        Request.params(key, value);
        return Request.get(new URI(url));
    }

    public ResponseOptions<Response> deleteWithPathParam(String url, String pathParam) throws URISyntaxException {
        return Request.delete(new URI(url.concat("/").concat(pathParam)));
    }

}
