package day07;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class NewsAPI_Test {

    @DisplayName("News API get all News Author if the Source id is not null")
    @Test
    public void testNews(){

        // http://newsapi.org/v2/top-headlines?country=us
        String apiToken = "42bb42f550eb432a90d48201b33380e5";

        Response response = given()

                .baseUri("http://newsapi.org")
                .basePath("/v2")
                .header("Authorization", "Bearer " + apiToken)
                .queryParam("country", "us")
                .log().all().
        when()
                .get("/top-headlines") ;


        JsonPath jp = response.jsonPath();

        List<String> allAuthor =  jp.getList("articles.author") ;
            System.out.println("allAuthor = " + allAuthor.size() );

        // allAuthor.forEach( eachAuthor -> System.out.println("eachAuthor = " + eachAuthor));
        // filter out the result by checking the source fields  --->> id filed null or not

        List<String> allAuthorFiltered =
                jp.getList("articles.findAll { it.source.id != null }.author");

        for (String author : allAuthorFiltered){

            System.out.println("author = " + author);
        }

        System.out.println("allAuthorFiltered = " + allAuthorFiltered.size() );

    }

}
