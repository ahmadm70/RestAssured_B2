package io.loopcamp.test.day01_intro;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;


public class ReqresApiTest {
    /**
     * When user sends GET request to "<a href="https://reqres.in/api/users/">...</a>"
     * Then RESPONSE STATUS CODE is 200
     * And RESPONSE BODY should contain "George"
     */
    String endPoint = "https://reqres.in/api/users/";

    @DisplayName("GET all users")
    @Test
    public void usersGetTest() {
        //Response response = RestAssured.get(endPoint);
        //Response response = get(endPoint);      //Since we did the static import, we can use get() directly
        Response response = when().get(endPoint);       //Does exact same thing with the line above (just for GHERKIN)
        Assertions.assertEquals(200, response.getStatusCode(), "Status codes aren't matching!");
        Assertions.assertTrue(response.asString().contains("George"), "Response body doesn't contain \"George\"");
    }
}