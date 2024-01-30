package io.loopcamp.practice.day02;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class Task1 {
    String endPoint = "https://jsonplaceholder.typicode.com/posts";

    @DisplayName("Q1")
    @Test
    public void q1() {
        Response response = given().accept(ContentType.JSON).when().get(endPoint);
        //System.out.println(response.asString());
        assertEquals(200, response.getStatusCode());
        assertTrue(response.contentType().contains(ContentType.JSON.toString()));
    }

    @DisplayName("Q2")
    @Test
    public void q2() {
        Response response = given().accept(ContentType.JSON).and().pathParam("id", 1).when().get(endPoint + "/{id}");
        assertEquals(200, response.getStatusCode());
        System.out.println(response.contentType());
        assertTrue(response.asString().contains("repellat provident"));
        assertTrue(response.contentType().contains(ContentType.JSON.toString()));
        assertEquals("Express", response.getHeader("X-Powered-By"));
        assertEquals("1000", response.getHeader("X-Ratelimit-Limit"));
        assertTrue(Integer.parseInt(response.getHeader("Age")) > 100);
        assertTrue(response.getHeader("NEL").contains("success_fraction"));
    }

    @DisplayName("Q3")
    @Test
    public void q3() {
        Response response = given().accept(ContentType.JSON).and().pathParam("id", 12345).when().get(endPoint + "/{id}");
        assertEquals(404, response.getStatusCode());
        assertTrue(response.asString().contains("{}"));
    }

    @DisplayName("Q4")
    @Test
    public void q4() {
        Response response = given().accept(ContentType.JSON).and().pathParam("id", 2).when().get(endPoint + "/{id}/comments");
        assertEquals(200, response.getStatusCode());
        assertTrue(response.contentType().contains(ContentType.JSON.toString()));
        assertTrue(response.asString().contains("Presley.Mueller@myrl.com") && response.asString().contains("Dallas@ole.me")
                && response.asString().contains("Mallory_Kunze@marie.org"));
    }

    @DisplayName("Q5")
    @Test
    public void q5() {
        Response response = given().accept(ContentType.JSON).and().pathParam("postId", 1).when().get(endPoint + "/{postId}/comments");
        assertEquals(200, response.getStatusCode());
        assertTrue(response.contentType().contains(ContentType.JSON.toString()));
        assertEquals("keep-alive", response.getHeader("Connection"));
        assertTrue(response.asString().contains("Lew@alysha.tv"));
    }

    @DisplayName("Q6")
    @Test
    public void q6() {
        Response response = given().accept(ContentType.JSON).and().pathParam("postId", 333).when().get(endPoint + "/{postId}/comments");
        assertEquals(200, response.getStatusCode());
        assertTrue(response.contentType().contains(ContentType.JSON.toString()));
        assertEquals("2", response.getHeader("Content-Length"));
        assertTrue(response.asString().contains("[]"));
    }
}