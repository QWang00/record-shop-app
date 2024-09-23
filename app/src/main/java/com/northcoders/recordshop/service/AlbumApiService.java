package com.northcoders.recordshop.service;

import com.northcoders.recordshop.model.Album;

import java.util.List;

import com.northcoders.recordshop.model.Album;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AlbumApiService {

    @GET("albums/all")
    Call<List<Album>> getAllAlbums();

    @POST("albums")
    Call<Album> addAlbum(@Body Album album);

//    @Multipart
//    @POST("/images/upload")
//    Call<String> uploadImage(@Part MultipartBody.Part file);
}
