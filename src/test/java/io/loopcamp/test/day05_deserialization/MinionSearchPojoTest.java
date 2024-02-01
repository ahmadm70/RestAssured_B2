package io.loopcamp.test.day05_deserialization;

import io.loopcamp.pojo.Minion;
import io.loopcamp.pojo.MinionSearch;
import io.loopcamp.utilities.MinionApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class MinionSearchPojoTest extends MinionApiTestBase {
    @DisplayName("GET /minions")
    @Test
    public void minionSearchPojoTest() {
        /*
        Response response = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "e")
                .and().queryParams("gender", "Female")
                .when().get("/minions/search");
        Response response = given().accept(ContentType.JSON)
                .and().queryParams("nameContains", "e", "gender", "Female")
                .when().get("/minions/search");
         */
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("nameContains", "e");
        queryParams.put("gender", "Female");
        Response response = given().accept(ContentType.JSON)
                .and().queryParams(queryParams)
                .when().get("/minions/search");
        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());
        MinionSearch minionSearch = response.as(MinionSearch.class);
        System.out.println(minionSearch.getTotalElement());
        System.out.println(minionSearch.getContent());
        System.out.println(minionSearch.getContent().get(0));
        System.out.println();
        for (Minion each : minionSearch.getContent()) {
            System.out.println(each.getId());
            System.out.println(each.getGender());
            System.out.println(each.getName());
            System.out.println(each.getPhone());
            System.out.println();
        }
    }
}