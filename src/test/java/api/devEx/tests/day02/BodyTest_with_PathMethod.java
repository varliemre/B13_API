package api.devEx.tests.day02;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.*;

public class BodyTest_with_PathMethod {

    String devExURL = "http://www.eurotech.study";

    @Test
    public void t12_devEx_getUserProfile_with_pathMethod(){

        /*
           {
            "id": 109,
            "email": "angelina@mynet.com",
            "name": "Angelina",
            "company": "Apple",
            "status": "Tester",
            "profileId": 52
            }
         */

        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParam("id", 109)
                .when()
                .get(devExURL + "/api/profile/userQuery");
        assertEquals(response.statusCode(),200);
        System.out.println("response.path(\"id\") = " + response.path("id"));
        System.out.println("response.path(\"email\") = " + response.path("email"));
        System.out.println("response.path(\"name\") = " + response.path("name"));
        System.out.println("response.path(\"company\") = " + response.path("company"));

        int actualID=response.path("id");
        String actualEmail=response.path("email");
        String actualName = response.path("name");

        assertEquals(actualID,109);
        assertEquals(actualEmail,"angelina@mynet.com");
        assertEquals(actualName,"Angelina");

    }

    @Test
    public void
}
