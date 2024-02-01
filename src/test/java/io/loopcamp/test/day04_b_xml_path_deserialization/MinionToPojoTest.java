package io.loopcamp.test.day04_b_xml_path_deserialization;

import io.loopcamp.pojo.Minion;
import io.loopcamp.utilities.MinionApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class MinionToPojoTest extends MinionApiTestBase {
    @DisplayName("GET /minions/{id}")
    @Test
    public void minionToPojoTest() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 10)
                .when().get("/minions/{id}");
        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());
        Minion minionObject = response.as(Minion.class);
        System.out.println(minionObject);
        assertEquals(10, minionObject.getId(), "IDs aren't matching!");
        assertEquals("Female", minionObject.getGender(), "Genders aren't matching!");
        assertEquals("Lorenza", minionObject.getName(), "Names aren't matching!");
        assertEquals("3312820936", minionObject.getPhone(), "Phones aren't matching!");
    }
}