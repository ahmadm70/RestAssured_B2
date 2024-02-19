package io.loopcamp.test.day02_a_headers;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class MinionHeadersTest {
    String endPoint = "http://44.211.67.23:8000/api/minions";

    @DisplayName("Get all headers for minions")
    @Test
    public void getAllMinionsHeadersTest() {
        when().get(endPoint).then().assertThat().statusCode(200).and().contentType(ContentType.XML);
    }

    @DisplayName("GET with requested header")
    @Test
    public void acceptTypeHeaderTest() {
        given().accept("application/json").when().get(endPoint).then().assertThat().statusCode(200).and().contentType(ContentType.JSON);
    }

    @DisplayName("Read response headers")
    @Test
    public void readResponseHeadersTest() {
        Response response = given().accept(ContentType.JSON).when().get(endPoint);
        Assertions.assertEquals(200, response.getStatusCode());
        System.out.println(response.getHeader("Date"));
        System.out.println(response.getHeader("Connection"));
        System.out.println(response.getHeaders());
        Assertions.assertFalse(response.getHeader("Keep-Alive").isEmpty());
    }
}