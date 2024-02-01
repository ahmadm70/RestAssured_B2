package io.loopcamp.test.day04_b_xml_path_deserialization;

import io.loopcamp.utilities.MinionApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class MinionToMapTest extends MinionApiTestBase {
    @DisplayName("GET /minions/{id}")
    @Test
    public void minionToMapTest() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 10)
                .when().get("/minions/{id}");
        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());
        Map <String, Object> minionMap = response.as(Map.class);
        System.out.println(minionMap);
        System.out.println(minionMap.keySet());
        System.out.println(minionMap.values());
        assertEquals(10, minionMap.get("id"), "IDs aren't matching!");
        assertEquals("Female", minionMap.get("gender"), "Genders aren't matching!");
        assertEquals("Lorenza", minionMap.get("name"), "Names aren't matching!");
        assertEquals("3312820936", minionMap.get("phone"), "Phones aren't matching!");
    }
}