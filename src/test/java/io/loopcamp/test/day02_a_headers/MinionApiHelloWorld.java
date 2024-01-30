package io.loopcamp.test.day02_a_headers;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class MinionApiHelloWorld {
    /**
     * When I send GET request to "http://your_ip:8000/api/hello"
     * Then status code should be 200
     * And response body should be equal to "Hello from Minion"
     * And content type is "text/plain;charset=UTF-8"
     */
    String endPoint = "http://54.92.243.31:8000/api/hello";

    @DisplayName("Hello from Minion")
    @Test
    public void helloApiTest() {
        Response response = when().get(endPoint);
        Assertions.assertEquals(200, response.getStatusCode());
        Assertions.assertEquals(response.asString(), "Hello from Minion");
        Assertions.assertTrue(response.contentType().contains("text/plain;charset=ISO-8859-1"));
    }

    @DisplayName("Hello from Minion chaining")
    @Test
    public void helloApiBDDTest() {
        when().get(endPoint).then().assertThat().statusCode(200).and().contentType("text/plain;charset=ISO-8859-1");
    }
}