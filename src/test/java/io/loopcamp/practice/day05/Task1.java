package io.loopcamp.practice.day05;

import io.loopcamp.utilities.ZippopotamusApiTestBase;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Task1 extends ZippopotamusApiTestBase {

    @DisplayName("Q1")
    @Test
    public void q1() {
        given().accept(ContentType.JSON)
                .and().pathParam("zipcode", 22031)
                .when().get("/us/{zipcode}")
                .then().statusCode(HttpStatus.SC_OK)
                .and().contentType(ContentType.JSON)
                .and().header("Server", is("cloudflare"))
                .and().header("Report-To", not(emptyOrNullString()))
                .and().body("'post code'", is("22031"),
                        "country", is("United States"),
                        "'country abbreviation'", is("US"),
                        "places[0].'place name'", is("Fairfax"),
                        "places[0].state", is("Virginia"),
                        "places[0].latitude", is("38.8604"));
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

    @DisplayName("Q3")
    @Test
    public void q3() {
        given().accept(ContentType.JSON)
                .and().pathParams("state", "VA", "city", "Fairfax")
                .when().get("/us/{state}/{city}")
                .then().statusCode(HttpStatus.SC_OK)
                .and().contentType(ContentType.JSON)
                .and().body("'country abbreviation'", is("US"),
                        "country", is("United States"),
                        "'place name'", is("Fairfax"),
                        "places[].'place name'", everyItem(containsString("Fairfax")),
                        "places[].'post code'", everyItem(startsWith("22")));
    }
}