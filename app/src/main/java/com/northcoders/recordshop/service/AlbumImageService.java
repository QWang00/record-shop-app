package com.northcoders.recordshop.service;

import com.northcoders.recordshop.model.AlbumImageModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AlbumImageService {
    @GET("search")
    Call<AlbumImageModel> searchAlbum(@Query("term") String albumNameWithArtist, @Query("entity") String entity);
}