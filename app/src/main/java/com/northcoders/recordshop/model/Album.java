package com.northcoders.recordshop.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.InverseMethod;
import androidx.databinding.library.baseAdapters.BR;

import com.google.gson.annotations.SerializedName;

public class Album extends BaseObservable {

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

    @SerializedName("imageUrl")
    private String imageUrl;

    public Album() {
    }

    public Album(String artist, String genre, Long id, String name, int releaseYear, String imageUrl) {
        this.artist = artist;
        this.genre = genre;
        this.id = id;
        this.name = name;
        this.releaseYear = releaseYear;
        this.imageUrl = imageUrl;
    }

    @Bindable
    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
        notifyPropertyChanged(BR.artist);
    }

    @Bindable
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
        notifyPropertyChanged(BR.genre);
    }

    @Bindable
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getName() {
        return name;
    }

    @Bindable
    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
        notifyPropertyChanged(BR.releaseYear);
    }

    @Bindable
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        notifyPropertyChanged(BR.imageUrl);
    }

    @InverseMethod("convertIntToString")
    public  int convertStringToInt(String value) {

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {

            return 0;
        }
    }

    public String convertIntToString(int value) {
        if(value == 0) return "";
        return String.valueOf(value);
    }
}
