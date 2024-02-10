package io.loopcamp.test.day09_a_authorization;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DocuportAccessToken {
    @Test
    public void docuportLoginTest() {
        String jsonBody = "{\"usernameOrEmailAddress\": \"b1g2_advisor@gmail.com\", \"password\": \"Group2\"}";
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .body(jsonBody)
                .when().post("https://beta.docuport.app/api/v1/authentication/account/authenticate");
        response.prettyPrint();
        assertThat(response.statusCode(), is(HttpStatus.SC_OK));
        String accessToken = response.path("user.jwtToken.accessToken");
        System.out.println(accessToken);
        assertTrue(accessToken != null && !accessToken.isEmpty());
    }
}