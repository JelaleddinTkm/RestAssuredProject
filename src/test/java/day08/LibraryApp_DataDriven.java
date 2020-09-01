package day08;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import utility.ConfigurationReader;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LibraryApp_DataDriven {

    /**
     *  --- Create a csv file under resources folder called credentials.csv
     *     -- it has 2 column , username , password
     *     --- copy the library1 username password I shared under codenote to create this file
     *     ----
     *     We will write a parameterized test for POST /login endpoint
     *     if the username and password is valid
     *         you should simply get 200 and the token field should not be null
     */


    @BeforeAll
    public static void init() {

        RestAssured.baseURI = ConfigurationReader.getProperty("library1.base_url");
        RestAssured.basePath = "/rest/v1";
    }


    // give a name your test in this format
    // itiration 1 | usernme : firstColData , password : secondColData

    @ParameterizedTest( name = "iteration {index} | username:{0} , password:{1}" )
    @CsvFileSource( resources = "/credentials.csv")
    public void testLoginCredentials( String user, String pass ) {

//        System.out.println("user = " + user);
//        System.out.println("pass = " + pass);

        // so now lets make a post request to /login
        // content type is x-www-form-urlencoded
        // form data  email , password
        // check if the status code 200  if the password is correct
        // check the token field from the response is not null

        given()
                .log().all()
                .contentType(ContentType.URLENC)
                .formParam("email", user )
                .formParam("password" , pass).
        when()
                .post("/login").
        then()
                .statusCode(200)
                .body("token", notNullValue() )
        ;
    }


    @AfterAll
    public static void cleanUp(){

        RestAssured.reset();
    }



}
