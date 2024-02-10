package io.loopcamp.utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class DocuportApiTestBase {

    @BeforeAll  // JUnite - @Before
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("docuport.base.url"); // Since we are doing static import from RestAssured, we can use baseUri which helps us for GET request concatenation in our test
    }
    //Make a method that gets the token
    public static String getAccessToken(String username) {

        String jsonBody = "{\n" +
                "\"usernameOrEmailAddress\": \"b1g2_" + username + "@gmail.com\",\n" +
                "\"password\": \"" + ConfigurationReader.getProperty("docuport.global.password") + "\"\n" +
                "}";
        Response response = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(jsonBody)
                .when().post("/api/v1/authentication/account/authenticate");
        String accessToken = response.path("user.jwtToken.accessToken");
        assertTrue(accessToken != null && !accessToken.isEmpty());
        return "Bearer " + accessToken;
    }
}