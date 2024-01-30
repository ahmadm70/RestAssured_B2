package io.loopcamp.practice.day04;

import io.loopcamp.utilities.HRApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class Task1 extends HRApiTestBase {
    @DisplayName("Q1")
    @Test
    public void q1() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("country_id", "US")
                .when().get("/countries/{country_id}");
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());
        JsonPath jsonPath = response.jsonPath();
        assertEquals("US", jsonPath.getString("country_id"));
        assertEquals("United States of America", jsonPath.getString("country_name"));
        assertEquals(10, jsonPath.getInt("region_id"));
    }

    @DisplayName("Q2")
    @Test
    public void q2() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"department_id\":80}")
                .when().get("/employees");
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());
        JsonPath jsonPath = response.jsonPath();
        List<String> jobIDs = jsonPath.getList("items.findAll{it}.job_id");
        for (String each : jobIDs) {
            assertTrue(each.startsWith("SA"), "Job IDs aren't matching!");
        }
        List<Integer> departmentIDs = jsonPath.getList("items.findAll{it}.department_id");
        for (Integer each : departmentIDs) {
            assertEquals(80, each, "Department IDs aren't matching!");
        }
        List<String> allResults = jsonPath.getList("items.findAll{it}");
        assertEquals(25, allResults.size(), "Total counts aren't matching!");
    }

    @DisplayName("Q3")
    @Test
    public void q3() {
        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"region_id\":30}")
                .when().get("/countries");
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        JsonPath jsonPath = response.jsonPath();
        List<Integer> regionsIDs = jsonPath.getList("items.findAll{it}.region_id");
        for (Integer each : regionsIDs) {
            assertEquals(30, each, "Region IDs aren't matching!");
        }
        List<String> allResults = jsonPath.getList("items.findAll{it}");
        assertEquals(6, allResults.size(), "Total counts aren't matching!");
        boolean hasMore = jsonPath.getBoolean("hasMore");
        assertFalse(hasMore, "hasMore value isn't matching!");
        List<String> actualCountryNames = jsonPath.getList("items.findAll{it}.country_name");
        List<String> expectedCountryNames = new ArrayList<>(Arrays.asList("China", "Israel", "India", "Japan", "Kuwait", "Singapore"));
        assertEquals(expectedCountryNames, actualCountryNames, "Country names aren't matching!");
    }
}