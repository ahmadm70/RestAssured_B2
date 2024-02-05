package io.loopcamp.test.day06_a_hamcrest;

import io.loopcamp.utilities.MinionApiTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class MinionHamcrestTest extends MinionApiTestBase {
    @DisplayName("GET /minions/{id}")
    @Test
    public void minionHamcrestTest() {
        given().accept(ContentType.JSON)
                .and().pathParam("id", 24)
                .when().get("/minions/{id}")
                .then().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().assertThat().body("id", is(24),
                        "name", equalTo("Julio"),
                        "gender", is("Male"),
                        "phone", is("9393139934"));
    }

    @DisplayName("GET /minions/search with query param")
    @Test
    public void searchTest() {
        given().accept(ContentType.JSON)
                .and().queryParam("nameContains", "e")
                .and().queryParam("gender", "Female")
                .when().get("/minions/search")
                .then().statusCode(200)
                .and().contentType(ContentType.JSON)
                //.and().header("Date", containsString("2024")) --> This is hard coded
                .and().header("Date", containsString("" + LocalDate.now().getYear()))
                .and().body("totalElement", is(33),
                        "content.id", hasItems(94, 98, 91, 81),
                        "content.name", hasItems("Jocelin", "Georgianne", "Catie", "Marylee", "Elita"),
                        "content.gender", everyItem(is("Female")),
                        "content.name", everyItem(containsStringIgnoringCase("e"))).log().all();
    }
}