package api.devEx.tests.day07;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class PostRequestDemo {


    @BeforeClass
    public void beforeClass() {
        baseURI="http://www.eurotech.study";
    }

    /* CT D07 create user
        given accept type and content type is JSON
        And request jsonBody is
                {
              "email": "emrebey5@gmail.com",
              "password": "987654",
              "name": "Emre B",
              "google": "emre_google",
              "facebook": "emre_facebook",
              "github": "emre_github"
            }
         When user sends POST request to '/api/users'
         Then status code 200
         And token should be created
    * */

    @Test
    public void t33_dx_registerUser_POST_newUser1() {

        String jsonBody = "{\n" +
                "              \"email\": \"emrebey5@gmail.com\",\n" +
                "              \"password\": \"987654\",\n" +
                "              \"name\": \"Emre B\",\n" +
                "              \"google\": \"emre_google\",\n" +
                "              \"facebook\": \"emre_facebook\",\n" +
                "              \"github\": \"emre_github\"\n" +
                "            }";

        Response response = given().accept(ContentType.JSON)  // hey api send me body as json format
                .and()
                .contentType(ContentType.JSON) //hey api I am sending json body
                .and()
                .body(jsonBody)
                .when()
                .post("/api/users");

        assertEquals(response.statusCode(),200);
        response.prettyPrint();
        assertTrue(response.body().asString().contains("token"));
    }
}
