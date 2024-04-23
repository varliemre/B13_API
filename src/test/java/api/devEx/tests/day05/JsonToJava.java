package api.devEx.tests.day05;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class JsonToJava {
    @BeforeClass
    public void setUp() {
        baseURI = "http://www.eurotech.study";
    }

    @Test
    public void t26_dx_getUserProfile_with_qp_jsonDataMap() {
        /*
                {
                    "id": 528,
                    "email": "eurotech@gmail.com",
                    "name": "Teacher",
                    "company": "Eurotech",
                    "status": "Instructor",
                    "profileId": 276
                }
         */

        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParam("id", 528)
                .when()
                .get("/api/profile/userQuery");
        response.prettyPrint();

        //response body --> convert json to Java --> De-Serialization
        Map<String ,Object> jsonDataMap=response.body().as(Map.class);
        // java is starting
        System.out.println("jsonDataMap = " + jsonDataMap);
        Object company=jsonDataMap.get("company");
        System.out.println("company = " + company);
        Object actualEmail=  jsonDataMap.get("email");
        assertEquals(actualEmail,"eurotech@gmail.com");
    }


}