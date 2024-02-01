package io.loopcamp.test.day04_b_xml_path_deserialization;

import io.loopcamp.pojo.Minion;
import io.loopcamp.utilities.MinionApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class AllMinionsPojoTest extends MinionApiTestBase {
    @DisplayName("GET /minions/{id}")
    @Test
    public void allMinionsPojoTest() {
        Response response = given().accept(ContentType.JSON)
                .when().get("/minions");
        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());
        JsonPath jsonPath = response.jsonPath();
        List<Minion> allMinions = jsonPath.getList("", Minion.class);       //"" makes it start from beginning
        List<Minion> allFemaleMinions = new ArrayList<>();
        for (Minion each : allMinions) {
            if (each.getGender().equals("Female")) {
                allFemaleMinions.add(each);
            }
        }
        System.out.println(allFemaleMinions);
        System.out.println(allFemaleMinions.size());
    }
}