package io.loopcamp.test.day07_a_db_vs_api_put_delete;

import io.loopcamp.utilities.ConfigurationReader;
import io.loopcamp.utilities.DatabaseUtilities;
import io.loopcamp.utilities.MinionApiTestBase;
import io.loopcamp.utilities.MinionRestUtilities;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class MinionApiAndDbValidationTest extends MinionApiTestBase {
    @Test
    public void postNewMinionThenValidateInDatabaseTest() {
        Map<String, Object> minion = new HashMap<>();
        minion.put("gender", "Male");
        minion.put("name", "PostVSDatabase");
        minion.put("phone", "1234567890");
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(minion)
                .when().post("/minions");
        assertThat(response.statusCode(), is(HttpStatus.SC_CREATED));
        assertThat(response.contentType(), containsString(ContentType.JSON.toString()));
        assertThat(response.path("success"), is("A Minion is Born!"));
        JsonPath jsonPath = response.jsonPath();
        int newMinionId = jsonPath.getInt("data.id");
        String query = "select minion_id, gender, name, phone from minions where minion_id = " + newMinionId;
        String databaseURL = ConfigurationReader.getProperty("minion.db.url");
        String databaseUsername = ConfigurationReader.getProperty("minion.db.username");
        String databasePassword = ConfigurationReader.getProperty("minion.db.password");
        DatabaseUtilities.createConnection(databaseURL, databaseUsername, databasePassword);
        Map<String, Object> databaseMap = DatabaseUtilities.getRowMap(query);
        assertThat(minion.get("GENDER"), equalTo(databaseMap.get("gender")));
        assertThat(minion.get("NAME"), equalTo(databaseMap.get("name")));
        assertThat(minion.get("PHONE"), equalTo(databaseMap.get("phone")));
        MinionRestUtilities.deleteMinionById(newMinionId);
        DatabaseUtilities.destroy();
    }
}