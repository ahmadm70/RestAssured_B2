package io.loopcamp.test.day04_a_json_path;

import io.loopcamp.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ZipCodeJsonApiTest {
    @BeforeEach
    public void setUp() {
        baseURI = ConfigurationReader.getProperty("zippopotamus.api.url");
    }

    @DisplayName("Get information by zipcode")
    @Test
    public void getInformationByZipcode() {
        Response response = given().accept(ContentType.JSON)
                .pathParam("country", "us")
                .pathParam("zipcode", "22031")
                .when().get("/{country}/{zipcode}");
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());
        JsonPath jsonPath = response.jsonPath();
        System.out.println(jsonPath.getString("'post code'"));      //If key has space in it, we've to use ''
        System.out.println(jsonPath.getString("country"));      //If there is no space, '' is optional
        System.out.println(jsonPath.getString("places[0].'state abbreviation'"));
        assertEquals("22031", jsonPath.getString("'post code'"));
        verifyZipCode("22301", jsonPath);
        assertEquals("United States", jsonPath.getString("country"));
        assertEquals("VA", jsonPath.getString("places[0].'state abbreviation'"));
        assertEquals("Virginia", jsonPath.getString("places[0].state"));
        assertEquals("38.8604", jsonPath.getString("places[0].latitude"));
    }
    public void verifyZipCode(String expectedZip, JsonPath jsonPath) {
        assertEquals("22031", jsonPath.getString("'post code'"));
    }
}