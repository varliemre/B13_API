package api.devEx.tests.day08;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class Update_with_PATCH_request {

    @BeforeClass
    public void beforeClass() {
        baseURI="http://www.eurotech.study";
    }

    @Test
    public void t42_dx_updateExperience_PATCH_request() {

        String email="emrebey4@gmail.com";
        String password="987654";
        Map<String,Object> experienceBody=new HashMap<>();
        experienceBody.put("title","Developer");
        experienceBody.put("company","Audi");
        experienceBody.put("location","Düsseldorf");

        given().log().all()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                //.header("x-auth-token",Authentication.getToken(email,password))   // first way
                .headers(Authentication.getAccessToken(email,password))             // second way
                .and()
                .body(experienceBody)
                .and()
                .pathParam("id",1695)
                .when()
                .patch("/api/profile/experience/{id}")
                .then().log().all()
                .assertThat().statusCode(204);
    }
}
