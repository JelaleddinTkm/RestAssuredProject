package day08;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import pojo.Spartan;
import utility.DB_Utility;

import java.util.HashMap;
import java.util.Map;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpartanAPI_E2E_HappyPath_Homework_3 {

    // we want the id that generated from post request accessible for all the
    static int newID;

    // we want exact order 1.Add , 2.Read , 3.Update , 4.Delete



    private static Spartan createRandomSpartanObject() {
        Faker faker = new Faker();
        String name   = faker.name().firstName();
        String gender = faker.demographic().sex();
        // always getting long number outside range of int to avoid errors
        long phone    = faker.number().numberBetween(5000000000L,9999999999L);
        Spartan randomSpartanObject = new Spartan(name,gender,phone);
        System.out.println("Created Random Spartan Object : " + randomSpartanObject);
        return randomSpartanObject ;
    }




    @Order(1)
    @Test
    public void testAddData(){
        // create one data here using the POJO as body, do your assertion
        // and grab the id so it can be used for all next 3 tests
        System.out.println("New ID is generated from the post request and saved " );
        newID = 100 ;
    }

    @Order(2)
    @Test
    public void testReadData(){
        // use the ID that have been generated from previous request
        // assert the response json according to expected data
        // expected data is the same data you used to create in previous request
        // you can make your post body from previous request at class level
        // so it can be accessible here
        // or you can also query the DB to get your expected data
        System.out.println(" The ID from Add Data Test is "  + newID);
        System.out.println("Using this ID for get Request ");
    }

    @Order(3)
    @Test
    public void testUpdateData(){
        System.out.println(" The ID from Add Data Test is "  + newID);
        System.out.println("Using this ID for PUT Request ");
    }

    @Order(4)
    @Test
    public void testDeleteData(){
        System.out.println(" The ID from Add Data Test is "  + newID);
        System.out.println("Using this ID for DELETE Request ");
    }



  //=============================================



    // example from horse_release
       /*

    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    public class SpartanAPI_E2E_HappyPath {

        private static int newID;
        private static String randomName;
        private static String randomGender;
        private static long phone;

        @BeforeAll
        public static void setUp(){
            RestAssured.baseURI="http://100.25.192.231";
            RestAssured.port=8000;
            RestAssured.basePath="/api";
            DB_Utility.createConnection("spartan1");
        }

        @Order(1)
        @Test
        public void testAddData() {
            randomName=new Faker().name().firstName();
            randomGender=new Faker().demographic().sex();
            phone=new Faker().number().numberBetween(1000000000L,9999999999L);
            Spartan sp1=new Spartan(randomName, randomGender, phone);
            Response response= given()
                    .contentType(ContentType.JSON)
                    .body(sp1)
                    .when()
                    .post("/spartans")
                    ;
            newID= response.path("data.id");
        }

        @Order(2)
        @Test
        public void testReadData(){
            // use the ID that have been generated from previous request
            // assert the response json according to expected data
            // expected data is the same data you used to create in previous request
            // you can make your post body from previous request at class level
            // so it can be accessible here
            // or you can also query the DB to get your expected data
            when()
                    .get("/spartans/{id}", newID)
                    .then()
                    .statusCode(200)
                    .log().all()
                    .body("name", is(randomName));
        }

        @Order(3)
        @Test
        public void testUpdateData(){
            String updatedName=new Faker().name().firstName();
            Map<String, Object> patchMap=new HashMap<>();
            patchMap.put(randomName,updatedName );
            given()
                    .log().all()
                    .contentType(ContentType.JSON)
                    .body(patchMap)
                    .when()
                    .patch("/spartans/{id}",newID)
                    .then()
                    .log().all()
                    .statusCode(204)
            ;
        }

        @Order(4)
        @Test
        public void testDeleteData(){
            when()
                    .delete("/spartans/{id}", newID).
                    then()
                    .log().all()
                    .statusCode(204);
        }
    }

 */



}
