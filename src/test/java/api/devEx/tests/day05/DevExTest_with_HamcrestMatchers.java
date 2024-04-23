package api.devEx.tests.day05;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

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
    public void t21_dx_getUserProfile_with_HamcrestMatcher_body() {
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

    @Test
    public void t22_getUserProfile_with_HamcrestMatcher_headers() {
        /*{
                "id": 25,
                "email": "jrdev@gmail.com",
                "name": "Jr. Dev",
                "company": "google",
                "status": "Junior Developer",
                "profileId": 1
            }
        * */

        given().accept(ContentType.JSON)
                .and().queryParam("id",25)
                .when().log().all()
                .get("/api/profile/userQuery")
                .then().assertThat().statusCode(200)
                .and()
                .header("Content-Type",Matchers.equalTo("application/json; charset=utf-8"))
                .and()
                .header("ETag",Matchers.equalTo("W/\"71-gLRrgzE02ZoB4TdrNnm1Irq0Rhc\""))
                .and()
                .header("Date",notNullValue())  // if date has value
                .and()
                .body("id",equalTo(25),
                        "email",equalTo("jrdev@gmail.com")
                    )

                .log().all();

    }
}
