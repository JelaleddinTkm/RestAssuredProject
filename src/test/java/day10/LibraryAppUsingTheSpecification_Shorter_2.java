package day10;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Catagory;
import pojo.User;
import utility.ConfigurationReader;
import utility.LibraryUtil;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.*;


public class LibraryAppUsingTheSpecification_Shorter_2 {


    static RequestSpecification requestSpec ;
    static ResponseSpecification responseSpec ;

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = ConfigurationReader.getProperty("library1.base_url");
        RestAssured.basePath = "/rest/v1" ;

        String theToken = LibraryUtil.loginAndGetToken("librarian69@library","KNPXrm3S");

        // just like we set the baseURI and basePath
        // we are using the static field of RestAssured to set it at global level

        RestAssured.requestSpecification =

            given()
                .accept(ContentType.JSON)                 // we want json back
                .log().all()                              // we want to log all
                .header("x-library-token", theToken) ;  // we want to set the token header


        // we are using the static field of RestAssured to set it at global level

        RestAssured.responseSpecification =

                 expect()
                         .statusCode(200)                 // expecting the Response status code 200
                         .contentType(ContentType.JSON)   // contentype is josn
                         .logDetail(LogDetail.ALL) ;      // want to log all of them

    }


    /**
     * Practice the De-Serialization using the same test
     * get the Map<String,String> object out of the response of GET /dashboard_stats
     * get the List<Category> object from the response of GET /get_book_categories
     * get the List<User> object from the response of GET /get_all_users
     * hint : you will need to create 2 POJO class called Category , User;
     *
     */
    @DisplayName("Testing GET /get_book_categories Endpoint with spec")
    @Test
    public void testLibrary(){

       Response response = when()
                .get("/get_book_categories");

       List<Catagory> catagoryList = response.jsonPath().getList("",Catagory.class);

            System.out.println("catagoryList = " + catagoryList);

        // above code is great, but what if I want to store each category as map rather than POJO
        // Each category is key value pair -->> Map
        // and we have any category -->> List<Map>
        // jsonPath methods always tru to help to convert the types where it can
        // in this case, each category in jsonArray we tried to store into map then get a list out of it
        // and Jackson databind take care of all conversation
        // List< Map<String,String> > categoryMapList = response.jsonPath().getList("" );

      List< Map<String,String> > categoryMapList = response.jsonPath().getList("" );
      // here we say -->> turn it into List of Map
            System.out.println("categoryMapList = " + categoryMapList);
    }


    @DisplayName("Testing GET /get_all_users Endpoint with spec")
    @Test
    public void testGetAllUsers(){

      Response response =  when().get(" /get_all_users");

      JsonPath jp = response.jsonPath();

      List<User> allUserList = jp.getList("", User.class);

           System.out.println("allUserList = " + allUserList);
    }



    //* get the Map<String,String> object out of the response of GET /dashboard_stats

    @DisplayName("Testing GET /dashboard_stats Endpoint with spec")
    @Test
    public void testGet_Dashboard_stats(){

        Response response =  when().get(" /dashboard_stats").prettyPeek();

        // if there is no path needed to get to what you are looking for
        // or if you wanted to point to your entire response , you can just provide ""

        Map<String,Integer> statMap = response.jsonPath().getMap("") ;

            System.out.println("statMap = " + statMap);
    }








}
