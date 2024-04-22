package api.devEx.tests.day03;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

public class DevEx_with_jsonPath {

    String devExURL = "http://www.eurotech.study";

    @Test
    public void t16_dx_getUserProfile_with_qp(){

        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParam("id", 904)
                .when()
                .get(devExURL + "/api/profile/userQuery");
        assertEquals(response.statusCode(),200);
        response.prettyPrint();

        /**
         * {
         *     "id": 904,
         *     "email": "sparrowjack@gmail.com",
         *     "name": "Jack",
         *     "company": "Samsung",
         *     "status": "QA",
         *     "profileId": 512
         * }
         */

        System.out.println("response.path(\"email\") = " + response.path("email"));
        System.out.println("*************************************");

        JsonPath jsonPath = response.jsonPath();

        int userIDJson=jsonPath.getInt("id");
        System.out.println("userIDJson = " + userIDJson);

        String emailJson = jsonPath.getString("email");
        String nameJson = jsonPath.getString("name");
        String companyJson = jsonPath.getString("company");
        String statusJson = jsonPath.getString("status");
        String profilIDJson = jsonPath.getString("profileId");

        System.out.println("emailJson = " + emailJson);
        System.out.println("nameJson = " + nameJson);
        System.out.println("companyJson = " + companyJson);
        System.out.println("statusJson = " + statusJson);
        System.out.println("profilIDJson = " + profilIDJson);

        assertEquals(userIDJson,904);
        assertEquals(emailJson,"sparrowjack@gmail.com");
        assertEquals(nameJson,"Jack");

        // in real test enviorenment we can test by this way
        assertEquals(jsonPath.getString("company"),"Samsung");
    }

    @Test
    public void t17_dx_getAllProfiles_jsonPathMethod_example(){
        Response response = given().accept(ContentType.JSON)
                .when()
                .get(devExURL + "/api/profile");

        assertEquals(response.statusCode(),200);
        JsonPath jsonPath = response.jsonPath();

        //8th ID
        int ninethID=jsonPath.getInt("id[8]");
        System.out.println("eightthID = " + ninethID);

        //get all companies
        List<String> allCompany = jsonPath.getList("company");
        System.out.println("allCompany.size() = " + allCompany.size());
        System.out.println("allCompany = " + allCompany);

        //get 2nd user info as Map
        Map<String, Object> secondUserInfo = jsonPath.getMap("user[1]");
        System.out.println("secondUserInfo = " + secondUserInfo);

        //get 2nd user's skills
        List<String> secondUserSkills = jsonPath.getList("skills[1]");// object in value si ne ise get i o sekilde cagir...
        System.out.println("secondUserSkills = " + secondUserSkills);

        System.out.println("*******************GPAT METHOD**********************\n");
        //get the names who have id less than 10
        List<String> lessThan10Names = jsonPath.getList("findAll{it.id<10}.user.name");   // it = if anlaminda
        System.out.println("lessThan10Names = " + lessThan10Names);

        // get IDa who have location info as
        List<String> locationLondon = jsonPath.getList("findAll{it.location=='London'}.id");
        System.out.println("locationLondon = " + locationLondon);
        System.out.println("locationLondon.size() = " + locationLondon.size());

    }
}
