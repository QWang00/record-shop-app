package com.northcoders.recordshop.model;

public class Album {

    private Long id;
    private String artist;
    private int releaseYear;
    private String genre;
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
