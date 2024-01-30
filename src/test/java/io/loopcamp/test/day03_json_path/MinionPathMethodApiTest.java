package io.loopcamp.test.day03_json_path;

import io.loopcamp.utilities.MinionApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class MinionPathMethodApiTest extends MinionApiTestBase {
    @DisplayName("GET Minions using path parameter")
    @Test
    public void getMinionsUsingPathParameter() {
        Response response = given().accept(ContentType.JSON).and().pathParam("id", 13).when().get("/minions/{id}");
        assertEquals(HttpStatus.SC_OK, response.getStatusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());
        System.out.println("" + response.path("id"));       //.path() returns us value of given path
        assertEquals(13, (int) response.path("id"));        //This returns Integer, that's why we are casting it
        assertEquals((Integer) 13, response.path("id"));
        assertEquals(13, (Integer) response.path("id"));
        //assertEquals((int) 13, response.path("id"));
        assertEquals("Jaimie", response.path("name"));
        assertEquals("Female", response.path("gender"));
        assertEquals("7842554879", response.path("phone"));
    }

    @DisplayName("GET /minions with path()")
    @Test
    public void readMinionJsonArrayUsingPathTest() {
        Response response = given().accept(ContentType.JSON).when().get("/minions");
        assertEquals(HttpStatus.SC_OK, response.statusCode());
        assertEquals(ContentType.JSON.toString(), response.contentType());
        // Print all IDs and names
        System.out.println("All IDs: " + response.path("id"));
        System.out.println("All names: " + response.path("name"));
        // Print 1st minion id and name
        System.out.println("1st minion id: " + response.path("[0].id"));
        System.out.println("1st minion id: " + response.path("id[0]")); // this will do the same thing.
        System.out.println("1st minion id: " + response.path("name[0]"));
        // Print last minion id and name -- > we just need to provide -1
        System.out.println("last minion id: " + response.path("id[-1]"));
        System.out.println("last minion id: " + response.path("name[-1]"));
        // System.out.println( "1st minion id: " + response.path("[-1].id")); // This will not find it.
        // Where can we store all the ids
        List<Integer> listId = response.path("id");
        // How many minions I have?
        System.out.println("Total Minions: " + listId.size());
        System.out.println("All Ids: " + listId);
        // How can you store all the names into List -- > Print all names and say Hi --- ? Hi $name!
        List<String> listName = response.path("name");
        for (int i = 0; i < listName.size(); i++) {
            System.out.println("Hi " + listName.get(i) + "!");
        }
        //System.out.println();
        //for (String each : listName) {
        //    System.out.println("Hi " + each + "!");
        //}
        //System.out.println();
        //listName.forEach(each -> System.out.println("Hi " + each + "!"));
    }
}