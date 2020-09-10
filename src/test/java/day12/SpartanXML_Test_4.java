package day12;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanXML_Test_4 {

    /*
        Practcice XML Response using GET /Spartans
        Create a class called SpartanXML_Test
        Add @BeforeAll method to set up your baseURI and basePath
        Create a Test and send GET /Spartans by specifying accept header as xml.
        Verify you get xml as response content type.
        Verify the first spartan name, id , gender .
     */

    // boilerplate goes here

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = ConfigurationReader.getProperty("spartan1.base_url");
        RestAssured.basePath = "/api";
    }


    @DisplayName("Test XML Response from GET /Spartans")
    @Test
    public void testSpartanXML(){

        given()
                .log().all()
                .accept(ContentType.XML).
        when()
               .get("/spartans").
        then()
                .log().all()
                .contentType(ContentType.XML)
                .statusCode(200)
                .body("List.item[0].name", is("Dowran Updated") )
                .body("List.item[0].gender", is("Male") )
                .body("List.item[0].id", is("102") )
        ;

        // XMLPath xp = response.xmlPath();
    }



    @DisplayName("XMLPath object to extract the data out")
    @Test
    public void testSpartanExtractData(){

        Response response =

        given()
                .accept(ContentType.XML).
         when()
                .get("/spartans") ;


        // getting XMLPath object just like we did for JsonPath object
        XmlPath xp = response.xmlPath();


        // get the first spartan id and store it into the int
        int firstID = xp.getInt("List.item[0].id");
            System.out.println("firstID = " + firstID);

        String firstName = xp.getString("List.item[0].name") ;
            System.out.println("firstName = " + firstName);

        long firstPhone = xp.getLong("List.item[0].phone");
            System.out.println("firstPhone = " + firstPhone);

        // get all the ids and store it into the list
        List<Integer> idList = xp.getList("List.item.id", Integer.class) ;
            System.out.println("idList = " + idList);

        // assert the list size is some number
        // assert above list has items 107,121,131
        // import static org.hamcrest.MatcherAssert.assertThat;
        // practice hamcrest matcher

        assertThat( idList, hasSize(128) ) ;

        // when we got the List<Integer> without specifying what type in getList method
        // somehow it can not decide the in the Hamcrest assertThat method it's a List<Integer>
        // so the fix was to provide class type for the getList method to make it obvious
        // like this  List<Integer> idList = xp.getList("List.item.id", Integer.class) ;

        assertThat(idList, hasItems(107,121,131));


            System.out.println("=============================2===============================");


            /*
                // Get a List of Long from the phone numbers
                // first check the size is 128
                // check it has few phone numbers you specified
                // optionally --
                // check every item is greaterThan 1 000 000 000
           */

        List<Long> phoneList = xp.getList("List.item.phone", Long.class) ;
            System.out.println("phoneList = " + phoneList);

        assertThat(phoneList, hasSize(128) );
        assertThat(phoneList, hasItems(1234567890l,7777777777l,1231231231l));
        assertThat(phoneList, everyItem( greaterThan(1000000000l) )) ;


        System.out.println("=============================3===============================");


        // Get a List of String from the names
        // find out how many unique names you have

        List<String> allNames = xp.getList("List.item.name");
            System.out.println("allNames = " + allNames);

        // Set interface define collection of unique elements
        // creating a HashSet object by copying everything from List , duplicates are auto-removed
        Set<String> uniqueNames = new HashSet<>(  allNames  );
            System.out.println("uniqueNames = " + uniqueNames);

            System.out.println("uniqueNames.size() = " + uniqueNames.size() );
            System.out.println("allNames.size() = " + allNames.size()  );



    }




}






