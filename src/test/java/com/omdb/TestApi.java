package com.omdb;

import org.junit.Test;

public class TestApi {

    FindMovie findMovie = new FindMovie();
    String apiKey = System.getProperty("apiKey","49e530b5"); // api key

    @Test
    public void Test_001_Harry_Potter_Assertion(){
        String searchWord = "harry potter";
        String movieTitle = "Harry Potter and the Sorcerer's Stone";
        String imdbId = findMovie.getMovieID(apiKey, searchWord, movieTitle); // İstedigimiz filmin id'sini aliyoruz
        findMovie.searchByID(apiKey, imdbId); // Alinan id ile film aramasi yapiyoruz
    }

    @Test
    public void Test_002_Batman_Assertion(){
        String searchWord = "batman";
        String movieTitle = "Batman: The Dark Knight Returns, Part 1";
        String imdbId = findMovie.getMovieID(apiKey, searchWord, movieTitle); // İstedigimiz filmin id'sini aliyoruz
        findMovie.searchByID(apiKey, imdbId); // Alinan id ile film aramasi yapiyoruz

    }

}
