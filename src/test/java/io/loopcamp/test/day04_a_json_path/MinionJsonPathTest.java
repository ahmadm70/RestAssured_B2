package io.loopcamp.test.day04_a_json_path;

import io.loopcamp.utilities.MinionApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class MinionJsonPathTest extends MinionApiTestBase {
    @DisplayName("GET /minions/{id}")
    @Test
    public void getMinionJsonPathTest() {
        Response response = given().accept(ContentType.JSON).and().pathParam("id", 13).when().get("/minions/{id}");
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());
        JsonPath jsonPath = response.jsonPath();
        assertEquals(13, jsonPath.getInt("id"));
        assertEquals("Female", jsonPath.getString("gender"));
        assertEquals("Jaimie", jsonPath.getString("name"));
        assertEquals("7842554879", jsonPath.getString("phone"));
    }
}