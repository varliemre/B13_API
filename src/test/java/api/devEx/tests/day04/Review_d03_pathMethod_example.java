package api.devEx.tests.day04;
import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class Review_d03_pathMethod_example {

    String bookStoreURL = "https://bookstore.toolsqa.com";
    String devExURL="http://www.eurotech.study";


    @Test
    public void t14_bs_getBooks_pathMethod(){
        /*
            TASK
            Given accept type json
            When user sends a get request to https://bookstore.toolsqa.com/BookStore/v1/Books
            Then status code should be 200
            And content type should be application/json; charset=utf-8
            And the first book isbn should be 9781449325862
            And the first book publisher should be O'Reilly Media
            */

        Response response = given().accept(ContentType.JSON)
                .when()
                .get(bookStoreURL + "/BookStore/v1/Books");

        //verify that status code should be 200
        assertEquals(response.statusCode(),200);

        //verify that content type
        String actualContentType = response.contentType();
        String expectedContentType = "application/json; charset=utf-8";
        assertEquals(actualContentType,expectedContentType);

        //Verify that the first book isbn should be 9781449325862

        String expected_isbn_num="9781449325862";
        String actual_isbn_num=response.path("books.isbn[0]");   // books.isbn[0]  da yazilabilir
        assertEquals(actual_isbn_num,expected_isbn_num);

        //Verify that the first book publisher should be O'Reilly Media

        String expected_publisher="O'Reilly Media";
        String actual_publisher=response.path("books[0].publisher");
        assertEquals(actual_publisher,expected_publisher);

    }

    @Test
    public void t15_dx_getAllProfiles_pathMethod_axample(){

        Response response = given().accept(ContentType.JSON)
                .when()
                .get(devExURL + "/api/profile");

        assertEquals(response.statusCode(),200);

        // findingfirst element's ID
        int first_ID=response.path("id[0]");
        System.out.println("first_ID = " + first_ID);

        //finding last elemt's ID
        int last_ID=response.path("id[-1]");
        System.out.println("last_ID = " + last_ID);

        //print second company
        String second_Company=response.path("company[1]");
        System.out.println("second_Company = " + second_Company);

        //get second skills as list
        List<String> second_Skills=response.path("skills[1]");
        System.out.println("second_Skills = " + second_Skills);
        System.out.println("second_Skills.size() = " + second_Skills.size());

        for (String secondSkill : second_Skills) {
            System.out.println("second_Skills = " + second_Skills);
        }
        System.out.println("**********************************************\n");
        //get second skills' 4th skill
        Object secondSkills4thSkill = response.path("skills[1][3]");
        System.out.println("secondSkills4thSkill = " + secondSkills4thSkill);
        System.out.println("**********************************************\n");
        //get 4 th user info
        Map<String,Object> fourthUserMap = response.path("user[3]");
        System.out.println("fourthUserMap = " + fourthUserMap);
        for (String user : fourthUserMap.keySet()) {
            //System.out.println("user = " + user);  // not use like this way
            System.out.println("user = " + fourthUserMap.get(user));
        }

        //get 4th user's ID
        Object fourthUserID = response.path("user[3].id");
        System.out.println("fourthUserID = " + fourthUserID);

        System.out.println("**********************************************\n");
        //get all user IDs and how many
        List<Integer> allUserID = response.path("user.id");   //index vermedigim icin user.id hepsini getirdi...
        System.out.println("allUserID = " + allUserID);
        System.out.println("allUserID.size() = " + allUserID.size());
        System.out.println("**********************************************\n");

        //get all IDs
        List<Integer> allIDs = response.path("id");
        System.out.println("allIDs = " + allIDs);
        System.out.println("allIDs.size() = " + allIDs.size());
        for (Integer id : allIDs) {
            System.out.println("id = " + id);
        }
    }
}
