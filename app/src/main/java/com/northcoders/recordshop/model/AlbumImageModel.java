package com.northcoders.recordshop.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AlbumImageModel {
    private List<AlbumResult> results;

    public List<AlbumResult> getResults() {
        return results;
    }

    public static class AlbumResult {
        @SerializedName("collectionName")
        private String albumName;

        @SerializedName("artworkUrl100")
        private String artworkUrl;

        public String getAlbumName() {
            return albumName;
        }

        public String getArtworkUrl() {
            return artworkUrl;
        }
    }
}
