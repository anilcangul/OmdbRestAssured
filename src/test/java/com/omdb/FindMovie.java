package com.omdb;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;

public class FindMovie {

    String baseURI = System.getProperty("baseURI","http://www.omdbapi.com/");

    public String getMovieID(String apiKey, String searchWord, String movieTitle) {

        RestAssured.baseURI = baseURI;
        String imdbId = null;

        try {
            Response response = getSearchResponse(apiKey,searchWord);
            JsonPath path = response.jsonPath(); // Json formatina donusturuyoruz
            List<MovieData> data = path.getList("Search", MovieData.class);

            for (MovieData object : data) {
                if (object.getTitle().equals(movieTitle)) { // Listedeki title aranilan title'a esit olmali
                    imdbId = object.getImdbID();
                    System.out.println("\n IMDb ID: " + imdbId);
                    break;
                }
            }
            return imdbId;
            }catch (Exception error){
                 System.out.println("HATA! Get Movie ID" + error.getMessage());
                 return null;
    }
    }
    public void searchByID(String apiKey, String imdbId) {

        try {
            given()
                    .param("apikey", apiKey)
                    .param("i",imdbId) // ID'si alinan film icin arama yapiyoruz
                    .when().get()
                    .then().log().all()
                    .statusCode(200)
                    .body("Title", not(emptyOrNullString()))
                    .body("Year", not(emptyOrNullString()))
                    .body("Released", not(emptyOrNullString())); //Title,Year,Released bos olmamali
            }catch (Exception error){
                System.out.println("HATA! Search By ID "+error.getMessage());
            }
    }
    private Response getSearchResponse(String apiKey, String searchWord) {

        try {
            return given()
                    .param("apikey", apiKey)
                    .param("s", searchWord)
                    .when().get()
                    .then().log().all()
                    .contentType(ContentType.JSON)
                    .statusCode(200) //status code 200 olmalı
                    .and()
                    .body("Search.Title",not(emptyOrNullString()))
                    .body("Search.Year",not(emptyOrNullString())) // Year, Title bos olmamali
                    .extract()
                    .response();
            }catch (Exception error){
                System.out.println("HATA! Search Response "+error.getMessage());
                return null;

            }
        }
    }


