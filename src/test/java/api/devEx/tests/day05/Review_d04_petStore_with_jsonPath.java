package api.devEx.tests.day05;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class Review_d04_petStore_with_jsonPath {

    //String petStoreURL = "https://petstore.swagger.io/v2";

    @BeforeClass
    public void setUp(){
        baseURI="https://petstore.swagger.io/v2";
    }


    @Test
    public void t18_ps_findPetByID_jsonPath_example(){

        Response response = given().accept(ContentType.JSON)
                .when()
                //.get( baseURI + "/pet/123");
                //.get( "/pet/123");   //    / koyamasakda calisir...
                .get( "pet/123");   // baseuri yazmasakda calisir. beforeclassdan dolayi...
        response.prettyPrint();
        /*
              {
                "id": 123,
                "category": {
                    "id": 123,
                    "name": "Malamute"
                },
                "name": "Pepper",
                "photoUrls": [
                    "https://fikiwiki.com/uploads/posts/2022-02/1644866304_45-fikiwiki-com-p-shchenki-krasivie-kartinki-49.jpg"
                ],
                "tags": [
                    {
                        "id": 123,
                        "name": "male"
                    }
                ],
                "status": "available"
            }
        */

        //print category id
        JsonPath jsonPath = response.jsonPath();
        int categoryID= jsonPath.getInt("category.id");
        System.out.println("categoryID = " + categoryID);
        assertEquals(categoryID,123);

        //print name Pepper
        String name = jsonPath.getString("name");
        System.out.println("name = " + name);

        //print tags name
        String nameTags = jsonPath.getString("tags[0].name");  //tags.name[0]
        System.out.println("nameTags = " + nameTags);

        //print photoUrls
        String url1= jsonPath.getString("photoUrls[0]");
        System.out.println("url1 = " + url1);

        String tags = jsonPath.getString("tags[0]");
        System.out.println("tags = " + tags);

    }

    @Test
    public void t19_ps_findPetByStatus_jsonPath() {
        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParam("status", "available")
                .when()
                .get("/pet/findByStatus");
        response.prettyPrint();
        assertEquals(response.statusCode(),200);
        // print all category names
        JsonPath jsonPath = response.jsonPath();
        List<Object> allCategoryNames=jsonPath.getList("category.name");
        System.out.println("allCategoryNames = " + allCategoryNames);
        System.out.println("allCategoryNames.size() = " + allCategoryNames.size());

        //print Cocktail
        String cocktail = jsonPath.getString("category[11].name");
        System.out.println("cocktail = " + cocktail);

    }
}

