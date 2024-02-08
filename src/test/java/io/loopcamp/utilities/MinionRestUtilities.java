package io.loopcamp.utilities;

import com.github.javafaker.Faker;
import io.loopcamp.pojo.Minion;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class MinionRestUtilities {
    private static final String baseUrl = ConfigurationReader.getProperty("minion.api.url");

    public static void deleteMinionById(int id) {
        given().accept(ContentType.JSON)
                .and().pathParam("id", id)
                .when().delete(baseUrl + "/minions/{id}");
    }

    public static Minion getNewMinionWithFakerData() {
        Minion minion = new Minion();
        Faker faker = new Faker();
        minion.setGender(faker.bool().bool() ? "Male" : "Female");
        minion.setName(faker.name().firstName());
        minion.setPhone(faker.numerify("703#######"));
        return minion;
    }
}