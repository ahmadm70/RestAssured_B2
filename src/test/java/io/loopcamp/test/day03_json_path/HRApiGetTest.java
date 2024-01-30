package io.loopcamp.test.day03_json_path;

import io.loopcamp.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class HRApiGetTest {
    @BeforeEach
    public void setUp() {
        //String url = ConfigurationReader.getProperty("hr.api.url");
        baseURI = ConfigurationReader.getProperty("hr.api.url");
    }

    @DisplayName("GET Regions")
    @Test
    public void getRegions() {
        //log.().all() --> shows all details for request and response
        //given().log().all().accept(ContentType.JSON).when().get(ConfigurationReader.getProperty("hr.api.url") + "/regions").then().assertThat().statusCode(200);
        Response response = given().accept(ContentType.JSON).when().get("/regions");        //With baseURI we don't have to concatenate
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());
        assertTrue(response.asString().contains("Europe"));
    }

    @DisplayName("GET Regions with path parameter")
    @Test
    public void getRegionsWithPathParameter() {
        Response response = given().accept(ContentType.JSON).and().pathParam("region_id", 10).when().get("/regions/{region_id}");
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());
        assertTrue(response.asString().contains("Europe"));
    }

    @DisplayName("GET Regions with query parameter")
    @Test
    public void getRegionsWithQueryParameter() {
        Response response = given().accept(ContentType.JSON).and().queryParam("q", "{\"region_name\": \"Americas\"}").when().get("/regions");
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());
        assertTrue(response.asString().contains("Americas"));
        assertTrue(response.asString().contains("20"));
    }
}