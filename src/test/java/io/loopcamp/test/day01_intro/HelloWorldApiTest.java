package io.loopcamp.test.day01_intro;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HelloWorldApiTest {
    String url = "https://sandbox.api.service.nhs.uk/hello-world/hello/world";
    @DisplayName("Hello World GET request")
    @Test
    public void helloWorldGetRequestTest() {
        //Send a GET request and save response inside the Response object
        Response response = RestAssured.get(url);
        //Print it out in a formatted way (JSON here)
        response.prettyPrint();
        //Print status code and line
        System.out.println(response.statusCode());
        System.out.println(response.statusLine());
        Assertions.assertEquals(200, response.statusCode(), "Status codes aren't matching!");
        //Returns response body as String
        String actualResponseBody = response.asString();
        System.out.println(actualResponseBody);
        Assertions.assertTrue(actualResponseBody.contains("Hello World!"));
    }
}