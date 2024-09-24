package com.northcoders.recordshop.model;


import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.InverseMethod;
import androidx.databinding.library.baseAdapters.BR;

import com.google.gson.annotations.SerializedName;

public class Album extends BaseObservable implements Parcelable {

    @SerializedName("id")
    private long id;
    @SerializedName("name")
    private String name;
    @SerializedName("artist")
    private String artist;
    @SerializedName("releaseYear")
    private int releaseYear;
    @SerializedName("genre")
    private String genre;


    public Album(long id, String name, String artist, int releaseYear, String genre) {
        this.id = id;
        this.name = name;
        this.artist = artist;
        this.releaseYear = releaseYear;
        this.genre = genre;

    }

    public Album() {
    }

    protected Album(Parcel in) {
        id = in.readLong();
        name = in.readString();
        artist = in.readString();
        releaseYear = in.readInt();
        genre = in.readString();

    }

    public static final Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };

    @Bindable
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }
    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
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
    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear){
        this.releaseYear = releaseYear;
        notifyPropertyChanged(BR.releaseYear);
    }
    @Bindable
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
        notifyPropertyChanged(BR.genre);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(artist);
        dest.writeInt(releaseYear);
        dest.writeString(genre);

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
