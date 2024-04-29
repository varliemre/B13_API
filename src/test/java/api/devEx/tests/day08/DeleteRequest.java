package api.devEx.tests.day08;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class DeleteRequest {

    @BeforeClass
    public void beforeClass() {
        baseURI="http://www.eurotech.study";
    }

    @Test
    public void t43_dx_deleteExperience() {

        String email="emrebey4@gmail.com";
        String password="987654";

        given().log().all()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .headers(Authentication.getAccessToken(email,password))
                .and()
                .and()
                .pathParam("id",1774)
                .when()
                .delete("/api/profile/experience/{id}")
                .then().log().all()
                .assertThat().statusCode(200);
    }
}
