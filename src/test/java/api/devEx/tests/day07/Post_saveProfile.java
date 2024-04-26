package api.devEx.tests.day07;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
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

    @Test
    public void t38_dx_registerUser_POST_newUser_and_verifyProfile() {

        String email ="emrebey11@gmail.com";
        String password="987654";
        String name ="Emre B";
        String google ="emre_google";
        String facebook ="emre_facebook";
        String github ="emre_github";


        Map<String,Object> requestMap = new HashMap<>();
        requestMap.put("email",email);
        requestMap.put("password",password);
        requestMap.put("name",name);
        requestMap.put("google",google);
        requestMap.put("facebook",facebook);
        requestMap.put("github",github);

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(requestMap)
                .when()
                .post("/api/users");

        assertEquals(response.statusCode(),200);
        response.prettyPrint();
        assertTrue(response.body().asString().contains("token"));
        String token = response.path("token");

        Map<String,Object> profileBody=new HashMap<>();
        profileBody.put("company","Mercedes");
        profileBody.put("website","www.mercedes.de");
        profileBody.put("location","Stutugart");
        profileBody.put("status","Developer");
        profileBody.put("skills","HTML,CSS,Javascript");
        profileBody.put("githubusername","String");
        profileBody.put("youtube","String");
        profileBody.put("twitter","String");
        profileBody.put("facebook","String");
        profileBody.put("linkedin","String");
        profileBody.put("instagram","String");

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

        // verify response body
        int id = response.path("user.id");

        response=given().accept(ContentType.JSON)
                .and()
                .queryParam("id",id)
                .when()
                .get("/api/profile/userQuery");

        assertEquals(response.statusCode(),200);

        //verify with path method
        assertEquals(response.path("email"),email);
        assertEquals(response.path("name"),name);

        //verify with hamcrest matchers
        given().accept(ContentType.JSON)
                .and()
                .queryParam("id",id)
                .when()
                .get("/api/profile/userQuery")
                .then()
                .assertThat()
                .body("company",equalTo("Mercedes"),"status",equalTo("Developer"));


    }
}
