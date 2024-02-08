package io.loopcamp.practice.day07;

import io.loopcamp.utilities.ConfigurationReader;
import io.loopcamp.utilities.DatabaseUtilities;
import io.loopcamp.utilities.HRApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class Task1 extends HRApiTestBase {
    @DisplayName("POST /regions/")
    @Test
    public void postRegionsTest() {
        Map<String, Object> newRegion = new HashMap<>();
        newRegion.put("region_id", 100);
        newRegion.put("region_name", "Test Region");
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(newRegion)
                .when().post("/regions/");
        assertThat(response.statusCode(), is(HttpStatus.SC_CREATED));
        assertThat(response.contentType(), containsString(ContentType.JSON.toString()));
        assertThat(response.path("region_id"), is(100));
        assertThat(response.path("region_name"), is("Test Region"));
    }

    @DisplayName("PUT /regions/100")
    @Test
    public void putThenDeleteTest() {
        String putBody = "{\"region_id\": 100, \"region_name\": \"Puzzle Region\"}";
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().pathParam("id", 100)
                .and().body(putBody)
                .when().put("/regions/{id}");
        assertThat(response.statusCode(), is(HttpStatus.SC_OK));
        assertThat(response.contentType(), containsString(ContentType.JSON.toString()));
        assertThat(response.path("region_id"), is(100));
        assertThat(response.path("region_name"), is("Puzzle Region"));
        given().accept(ContentType.JSON)
                .and().pathParam("id", 100)
                .when().delete("/regions/{id}")
                .then().statusCode(HttpStatus.SC_OK);
    }

    @DisplayName("POST /regions/ with database")
    @Test
    public void postNewRegionAndValidateDatabaseTest() {
        Map<String, Object> newRegion = new HashMap<>();
        newRegion.put("region_id", 200);
        newRegion.put("region_name", "Test Region");
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(newRegion)
                .when().post("/regions/");
        assertThat(response.statusCode(), is(HttpStatus.SC_CREATED));
        assertThat(response.contentType(), containsString(ContentType.JSON.toString()));
        String query = "select region_id, region_name from regions where region_id = " + 200;
        String databaseURL = ConfigurationReader.getProperty("hr.db.url");
        String databaseUsername = ConfigurationReader.getProperty("hr.db.username");
        String databasePassword = ConfigurationReader.getProperty("hr.db.password");
        DatabaseUtilities.createConnection(databaseURL, databaseUsername, databasePassword);
        Map<String, Object> databaseMap = DatabaseUtilities.getRowMap(query);
        assertThat(newRegion.get("REGION_ID"), equalTo(databaseMap.get("region_id")));
        assertThat(newRegion.get("REGION_NAME"), equalTo(databaseMap.get("region_name")));
        given().accept(ContentType.JSON)
                .and().pathParam("id", 200)
                .when().delete("/regions/{id}")
                .then().statusCode(HttpStatus.SC_OK);
    }
}