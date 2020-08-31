package myPractices;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;


public class SpartanTestWithPathMethod {

    @BeforeAll
    public static void init(){

        baseURI = "http://3.85.141.124" ;
        port = 8000 ;

        // if we do static our import Restassured,
        // then we do not need to use like Restassured.baseURI
        // alone baseURI works.

    }

    /**
     * given accept type is json
     * and path param id is 10
     * when user sends a get request to "/spartans/{id}"
     * then status code is 200
     * and content type is "application/json;char"
     * and response payload value match the following
     * {
     *     "id": 10,
     *     "name": "Dowran",
     *     "gender": "Female",
     *     "phone": 1234567890
     * }
     */


    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 10).
        when()
                .get("api/spartans/{id}");

    //    response.body().prettyPrint();

        //verify status code
        assertEquals(response.statusCode(), 200);

        //verify content type
        assertEquals(response.contentType(), "application/json;charset=UTF-8");

        //printing values of json keys
        System.out.println("id : " + response.body().path("id").toString());
        System.out.println("name : " + response.body().path("name").toString());
        System.out.println("gender : " + response.body().path("gender").toString());
        System.out.println("phone : " + response.body().path("phone").toString());

        int id = response.body().path("id");
        String name = response.body().path("name");
        String gender = response.path("gender");
        int phone = response.path("phone");

        //verify json keys and values
        assertEquals(id, 10);
        assertEquals(name, "Dowran");
        assertEquals(gender, "Female");
        assertEquals(phone, 1234567890);

    }

    @Test
    public void test2(){
        Response response = get("api/spartans/");

        // extract first id
        int firstID = response.path("id[0]");
        System.out.println("firstID = " + firstID);

        // extract first name
        String firstName = response.path("name[0]");
        System.out.println("firstName = " + firstName);

        // extract all firstNames and print them
        List<String> names = response.path("name");
        System.out.println("names = " + names);
        System.out.println("names = " + names.size());

        // if we want phone numbers one by one
        List<Object> phoneNumbers = response.path("phone");
        for( Object eachPhoneNumber : phoneNumbers){
            System.out.println( eachPhoneNumber );
        }


    }

}
