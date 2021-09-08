package specs;

import org.junit.Before;

public class TestConfig {
    String baseURL;

    @Before
    public void before() {
       baseURL = "https://reqres.in/";
    }
}
