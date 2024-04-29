package api.devEx.tests.day08;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class Authentication {

    @BeforeClass
    public void beforeClass() {
        baseURI="http://www.eurotech.study";
    }

    @Test
    public void t39_dx_loginAndGetToken() {


        String loginBody="{\n" +
                "  \"email\": \"emrebey4@gmail.com\",\n" +
                "  \"password\": \"987654\"\n" +
                "}";

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(loginBody)
                .when()
                .post("/api/auth");
        assertEquals(response.statusCode(),200);
        //response.prettyPrint();
        String token = response.path("token");
        System.out.println("token = " + token);
    }

    //we dont give a test number
    public static String getToken() {
        Map<String,Object> tokenMap=new HashMap<>();
        tokenMap.put("email","emrebey4@gmail.com");
        tokenMap.put("password","987654");
        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(tokenMap)
                .when()
                .post("/api/auth");

        assertEquals(response.statusCode(),200);
        String token = response.path("token");
        return token;
    }

    public static String getToken(String email, String password) {
        Map<String,Object> tokenMap=new HashMap<>();
        tokenMap.put("email",email);
        tokenMap.put("password",password);
        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(tokenMap)
                .when()
                .post("/api/auth");

        assertEquals(response.statusCode(),200);
        String token = response.path("token");
        return token;
    }

    public static Map<String, Object> getAccessToken(String email, String password) {
        Map<String,Object> tokenMap=new HashMap<>();
        tokenMap.put("email",email);
        tokenMap.put("password",password);

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(tokenMap)
                .when()
                .post("/api/auth");

        assertEquals(response.statusCode(),200);
        String token = response.path("token");

        Map<String,Object> authorization = new HashMap<>();
        authorization.put("x-auth-token",token);

        return authorization;
    }




}
