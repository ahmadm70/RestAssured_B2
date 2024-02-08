package io.loopcamp.test.day06_b_post_put_delete;

import io.loopcamp.pojo.Minion;
import io.loopcamp.utilities.MinionApiTestBase;
import io.loopcamp.utilities.MinionRestUtilities;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class MinionPostThenGet extends MinionApiTestBase {
    Minion minion = MinionRestUtilities.getNewMinionWithFakerData();

    @Test
    public void postNewMinionThenGetTest() {
        System.out.println("New minion info: " + minion);
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(minion)     //Serialization (parsing) from Minion Pojo object to JSON
                .when().post("/minions");
        response.prettyPrint();
        assertThat(response.statusCode(), is(HttpStatus.SC_CREATED));
        int newMinionID = response.jsonPath().getInt("data.id");
        Response response1 = given().accept(ContentType.JSON)
                .and().pathParam("id", newMinionID)
                .when().get("/minions/{id}"); // GET request
        System.out.println("GET request response: ");
        response1.prettyPrint();
        // DESERIALIZATION -- > convert the JSON response to Minion POJO class
        Minion minionFromGET = response1.as(Minion.class);
        // Compare the POSTed MINION with the one from GET MINION
        assertThat(minionFromGET.getName(), equalTo(minion.getName()));
        assertThat(minionFromGET.getGender(), equalTo(minion.getGender()));
        assertThat(minionFromGET.getPhone() + "", equalTo(minion.getPhone() + ""));
        // Once I am done with the assertions, I can DELETE
        MinionRestUtilities.deleteMinionById(newMinionID);
    }
}