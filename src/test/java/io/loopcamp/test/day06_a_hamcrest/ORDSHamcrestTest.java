package io.loopcamp.test.day06_a_hamcrest;

import io.loopcamp.utilities.HRApiTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class ORDSHamcrestTest extends HRApiTestBase {
    String countryId;

    @DisplayName("GET /countries with Hamcrest")
    @Test
    public void countriesTest() {
        countryId = given().accept(ContentType.JSON)
                .when().get("/countries")
                .then().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().body("count", is(25),
                        "items.country_id", hasItems("AR", "AU", "BE", "BR", "CA"),
                        "items.country_name", hasItems("Argentina", "Australia", "Belgium", "Brazil", "Canada"),
                        "items[0].country_id", is("AR"))
                .and().extract().body().path("items[0].country_id");
        System.out.println(countryId);
        given().accept(ContentType.JSON)
                .and().pathParam("country_id", countryId)
                .when().get("/countries/{country_id}")
                .then().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().body("country_name", is("Argentina"),
                        "country_id", is(countryId),
                        "region_id", is(20));
    }

    @DisplayName("GET /countries/{country_id}")
    @Test
    public void singleCountryTest() {
    }
}