package io.loopcamp.practice.day05;

import io.loopcamp.pojo.ZippopotamusPlaces;
import io.loopcamp.pojo.ZippopotamusSearch;
import io.loopcamp.utilities.ZippopotamusApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class Task1WithPojo extends ZippopotamusApiTestBase {
    @DisplayName("Q1 with Pojo")
    @Test
    public void q1WithPojo() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("zipcode", 22031)
                .when().get("/us/{zipcode}");
        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());
        assertEquals("cloudflare", response.header("Server"));
        assertFalse(response.header("Report-To").isEmpty());
        ZippopotamusSearch zippopotamusSearch = response.as(ZippopotamusSearch.class);
        assertEquals("22031", zippopotamusSearch.getPostCode());
        assertEquals("United States", zippopotamusSearch.getCountry());
        assertEquals("US", zippopotamusSearch.getCountryAbbreviation());
        assertEquals("Fairfax", zippopotamusSearch.getPlaces().get(0).getPlaceName());
        assertEquals("Virginia", zippopotamusSearch.getPlaces().get(0).getState());
        assertEquals("38.8604", zippopotamusSearch.getPlaces().get(0).getLatitude());
    }

    @DisplayName("Q2")
    @Test
    public void q2() {
        given().accept(ContentType.JSON)
                .and().pathParam("zipcode", 5000)
                .when().get("/us/{zipcode}")
                .then().statusCode(HttpStatus.SC_NOT_FOUND)
                .and().contentType(ContentType.JSON);
    }


    @DisplayName("Q3 with Pojo")
    @Test
    public void q3WithPojo() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParams("state", "VA", "city", "Fairfax")
                .when().get("/us/{state}/{city}");
        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());
        ZippopotamusSearch zippopotamusSearch = response.as(ZippopotamusSearch.class);
        assertEquals("US", zippopotamusSearch.getCountryAbbreviation());
        assertEquals("United States", zippopotamusSearch.getCountry());
        assertEquals("Fairfax", zippopotamusSearch.getPlaceName());
        for (ZippopotamusPlaces each : zippopotamusSearch.getPlaces()) {
            assertTrue(each.getPlaceName().contains("Fairfax"));
            assertTrue(each.getPostCode().startsWith("22"));
        }
    }
}