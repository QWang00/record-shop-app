package com.northcoders.recordshop.service;

import com.northcoders.recordshop.model.Album;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface AlbumApiService {

    @GET("all")
    Call<List<Album>> getAllAlbums();

    @POST("album")
    Call<Album> addAlbum(@Body Album album);

//    @Multipart
//    @POST("/images/upload")
//    Call<String> uploadImage(@Part MultipartBody.Part file);
}
