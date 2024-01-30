package io.loopcamp.test.day03_json_path;

import io.loopcamp.utilities.HRApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HRPathMethodApiTest extends HRApiTestBase {
    @DisplayName("GET /countries with .path()")
    @Test
    public void getCountriesWithPathMethod() {
        Response response = given().accept(ContentType.JSON).when().get("/countries");
        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());
        assertEquals("AR", response.path("items[0].country_id"));
        assertEquals("AR", response.path("items.country_id[0]"));       //Same thing with the line above
        List<String> names = response.path("items.country_id");
    }
}