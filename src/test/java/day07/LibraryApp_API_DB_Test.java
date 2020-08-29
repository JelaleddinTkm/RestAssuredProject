package day07;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;
import utility.DB_Utility;
import utility.LibraryUtil;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class LibraryApp_API_DB_Test {

    private static String libraryToken;

    @BeforeAll
    public static void init(){

    /*
//        RestAssured.baseURI = ConfigurationReader.getProperty("library1.base_url");
//        RestAssured.basePath = "/rest/v1";
//
//        // add a utility class that contains below method
//        libraryToken = LibraryUtil.loginAndGetToken(
//                ConfigurationReader.getProperty("library1.librarian_username") ,
//                ConfigurationReader.getProperty("library1.librarian_password"));
//
//        DB_Utility.createConnection("library1");

// all above codes we removed to LibraryUtil class and instead of it we will use below code

 */

    //    libraryToken  = LibraryUtil.setUpRestAssuredAndDB_forEnv("library1");
    //    libraryToken  = LibraryUtil.setUpRestAssuredAndDB_forEnv("library2");

        String active_env = ConfigurationReader.getProperty("active_env");
        libraryToken = LibraryUtil.setUpRestAssuredAndDB_forEnv(active_env) ;
    }


    @DisplayName("Validating the /dashboard_stats endpoint data against Database")
    @Test
    public void test(){
        System.out.println("libraryToken = " + libraryToken);

        // we will make a call to /Dashboard_stats endpoint and validate the data against database data
        DB_Utility.runQuery("SELECT count(*) FROM books");
            String bookCount = DB_Utility.getColumnDataAtRow(1,1);
                System.out.println("bookCount = " + bookCount);

        DB_Utility.runQuery("SELECT count(*) FROM users");
            String userCount = DB_Utility.getColumnDataAtRow(1,1);
                System.out.println("userCount = " + userCount);

        DB_Utility.runQuery("SELECT count(*) FROM book_borrow WHERE is_returned=false");
            String borrowedBookCount = DB_Utility.getColumnDataAtRow(1,1);
                System.out.println("borrowedBookCount = " + borrowedBookCount);

        given()
                .log().all()
                .header("x-library-token", libraryToken).
        when()
                .get("/dashboard_stats").
        then()
                .body("book_count", is(bookCount) )
                .body("borrowed_books", is(borrowedBookCount) )
                .body("users", is(userCount) )
        ;

    }


    @AfterAll
    public static void destroy(){
        DB_Utility.destroy();
    }




}
