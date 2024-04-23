package api.devEx.tests.day05;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class DevExTest_with_HamcrestMatchers {

    @BeforeClass
    public void setUp(){
        baseURI="http://www.eurotech.study";
    }

    @Test
    public void t20_dx_getUserProfile_with_qp() {

            /* class Task d5
            Given accept type is json
            And query param id 528
            When user sends GET request to /api/profile/userQuery
            Then status code 200
            and content type is application/json; charset=utf-8
            verify body  ===>> test21
            */

        given().accept(ContentType.JSON)
                .log().all()  // to print on console for request
                .and()
                .queryParam("id",528)
                .when()
                .get("/api/profile/userQuery")
                .then()
                .log().all()    // to print on console for response headers and body
                .statusCode(200)
                .and()
                .assertThat()
                .contentType("application/json; charset=utf-8");

    }

    @Test
    public void t21_dx_getUserProfile_with_HamcrestMatcher() {
        /*{
            "id": 528,
            "email": "eurotech@gmail.com",
            "name": "Teacher",
            "company": "Eurotech",
            "status": "Instructor",
            "profileId": 276
        }*/

        given().accept(ContentType.JSON)
                .and()
                .queryParam("id",528)
                .when()
                .get("/api/profile/userQuery")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .assertThat()
                .contentType("application/json; charset=utf-8")
                .body("id", Matchers.equalTo(528),
                        "email",Matchers.equalTo("eurotech@gmail.com"),
                        "name",Matchers.equalTo("Teacher"),"status",Matchers.equalTo("Instructor"),
                        "company",Matchers.equalTo("Eurotech"),"profileId",Matchers.equalTo(276)
                        );

    }
}
