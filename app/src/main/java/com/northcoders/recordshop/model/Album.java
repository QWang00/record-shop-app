package com.northcoders.recordshop.model;

import com.google.gson.annotations.SerializedName;

public class Album {

    @SerializedName("id")
    private Long id;

    @SerializedName("artist")
    private String artist;

    @SerializedName("releaseYear")
    private int releaseYear;

    @SerializedName("genre")
    private String genre;

    @SerializedName("name")
    private String name;

    public Album() {
    }

    public Album(String artist, String genre, Long id, String name, int releaseYear) {
        this.artist = artist;
        this.genre = genre;
        this.id = id;
        this.name = name;
        this.releaseYear = releaseYear;
    }
}
