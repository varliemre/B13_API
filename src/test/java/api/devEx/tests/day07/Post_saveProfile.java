package api.devEx.tests.day07;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Post_saveProfile {

    @BeforeClass
    public void beforeClass() {
        baseURI="http://www.eurotech.study";
    }

    @Test
    public void t37_dx_registerUser_POST_newUser_and_saveProfile() {

        /*  CT d07 create user and save profile
            create a new user
            verify with token
            save user profile with using token
        */
        NewUserInfo newUserInfo= new NewUserInfo("emrebey10@gmail.com", "987654","Emre B",
                "emre_google","emre_facebook","emre_github");
        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(newUserInfo)  // Serialization   ==>> java to json
                .when()
                .post("/api/users");

        assertEquals(response.statusCode(),200);
        response.prettyPrint();
        assertTrue(response.body().asString().contains("token"));
        String token = response.path("token");

        String profileBody = "{\n" +
                "  \"company\": \"Mercedes\",\n" +
                "  \"website\": \"www.mercedes.de\",\n" +
                "  \"location\": \"Stutugart\",\n" +
                "  \"status\": \"Developer\",\n" +
                "  \"skills\": \"HTML,CSS,Javascript\",\n" +
                "  \"githubusername\": \"string\",\n" +
                "  \"youtube\": \"string\",\n" +
                "  \"twitter\": \"string\",\n" +
                "  \"facebook\": \"string\",\n" +
                "  \"linkedin\": \"string\",\n" +
                "  \"instagram\": \"string\"\n" +
                "}";

        response=given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .header("x-auth-token",token)
                .and()
                .body(profileBody)
                .when()
                .post("/api/profile");
        response.prettyPrint();

    }
}
