package com.omdb;

import lombok.Data;

@Data
public class MovieData {
    private String Title;
    private String Year;
    private String imdbID;
    private String Type;
    private String Poster;
}

//Lombok kütüphanesi get-set methodlarını tekrar etmemek ve daha temiz bir kod için tercih edilmiştir.