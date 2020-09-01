package day09;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.Matchers.*;


public class SpartanRoleBasedAccessControlNegativeTest_3 {

    /**
     * -------  We are doing a role based access control test
     *      -- for the Spartan app with username password
     *      for the credentials  user/user
     *
     *        user should not be able to delete data
     *        user should not be able to post data
     *        user should not be able to update data
     *
     *        all these 3 tests share same username and password
     *        and we can also add accept json result back
     *        and we want to log all the request
     *
     *         all these test can share same response status as 403
     *         and all tests response content type is json
     *         and all test has Date header not null assertion
     *         and we want to see the log of all request
     */

    @BeforeAll
    public static void init(){

//we are using Akbar's ip address because it is with basic auth,
// and my ip is with no auth, so i will not be able to complete the test with my own

        RestAssured.baseURI = "http://54.160.106.84";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";
    }


    @DisplayName("User should not be able to delete data")
    @Test
    public void testUserCanNotDeleteData(){

        given()
                .auth().basic("user","user")
                .accept(ContentType.JSON)
                .log().all().
        when()
                .delete("/spartans/{id}" , 10).
        then()
                .statusCode(403)
                .contentType(ContentType.JSON)
                .header("Date", is( notNullValue() ) ) // checking Date header is not null
                .log().all();
    }


    @DisplayName("User should not be able to update data")
    @Test
    public void testUserCanNotUpdateData(){

        Spartan spartanObj = new Spartan("some name", "Male", 1234567890l) ;

        given()
                .auth().basic("user", "user")
                .accept(ContentType.JSON)
                .log().all()
                .contentType(ContentType.JSON)
                .body( spartanObj ).
        when()
                .put("/spartans/{id}", 10).
        then()
                .statusCode(403)
                .contentType(ContentType.JSON)
                .header("Date", is( notNullValue() ) ) // checking Date header is not null
                .log().all() ;

    }

    @DisplayName("User should not be able to post data")
    @Test
    public void testUserCanNotPostData(){

        Spartan spartanObj = new Spartan("some name", "Male", 1234567890l) ;

        given()
                .auth().basic("user", "user")
                .accept(ContentType.JSON)
                .log().all()
                .contentType(ContentType.JSON)
                .body( spartanObj ).
        when()
                .post("/spartans").

        then()
                .statusCode(403)
                .contentType(ContentType.JSON)
                .header("Date", is( notNullValue() ) ) // checking Date header is not null
                .log().all() ;

    }

/**
 * There is no destroy in RestAssured,
 * destroy is the method we created in DB_Utility to close DB connection
 *
 * destroy() method is used after you use Data base connection
 * to close all your three interfaces Connection, Statement and resultSet.
 */



}
