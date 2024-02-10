package io.loopcamp.test.day09_a_authorization;

import io.loopcamp.utilities.DocuportApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class DocuportApiTest extends DocuportApiTestBase {
    @Test
    public void getAllDepartmentsTest() {
        String accessToken = getAccessToken("employee");
        Response response = given().accept(ContentType.JSON)
                .and().header("Authorization", accessToken)
                .when().get("/api/v1/identity/departments/all");
        assertThat(response.statusCode(), is(HttpStatus.SC_OK));
        assertThat(response.contentType(), containsString(ContentType.JSON.toString()));
        assertThat(response.path("displayText"), hasItems("Tax", "Bookkeeping"));
    }

    @Test
    public void getAllStatesTest() {
        Response response = given().accept(ContentType.JSON)
                .and().header("Authorization", getAccessToken("supervisor"))
                .when().get("/api/v1/company/states/all");
        assertThat(response.statusCode(), is(HttpStatus.SC_OK));
        assertThat(response.contentType(), containsString(ContentType.JSON.toString()));
        assertThat(response.path("name"), hasItem("Washington D.C."));
    }

    @Test
    public void getAllClientsTest() {
        List<Map<String, Object>> allClientsList = given().accept(ContentType.JSON)
                .and().header("Authorization", getAccessToken("supervisor"))
                .when().get("/api/v1/document/clients/all")
                .then().statusCode(HttpStatus.SC_OK)
                .and().contentType(ContentType.JSON)
                .and().extract().as(List.class);
        System.out.println(allClientsList);
        System.out.println(allClientsList.size());
        assertThat(allClientsList.get(0).get("id"), is(31));
        assertThat(allClientsList.get(0).get("name"), is("3tseT"));
        assertThat(allClientsList.get(0).get("clientType"), is(1));
        assertThat(allClientsList.get(0).get("isActive"), is(true));
        assertThat(allClientsList.get(0).get("advisor"), is(nullValue()));
    }
}