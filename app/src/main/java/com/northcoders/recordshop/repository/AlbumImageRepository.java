package com.northcoders.recordshop.repository;


import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.util.List;

import com.northcoders.recordshop.R;

import com.northcoders.recordshop.model.AlbumImageModel;
import com.northcoders.recordshop.service.RetrofitInstance;
import com.northcoders.recordshop.service.AlbumImageService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumImageRepository {
    private AlbumImageService albumImageService;

    public void fetchAlbumImage(String albumNameWithArtist, ImageView imageView) {
        albumImageService = RetrofitInstance.getImageService();
        Call<AlbumImageModel> call = albumImageService.searchAlbum(albumNameWithArtist, "album");

        call.enqueue(new Callback<AlbumImageModel>() {
            @Override
            public void onResponse(@NonNull Call<AlbumImageModel> call, @NonNull Response<AlbumImageModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<AlbumImageModel.AlbumResult> albums = response.body().getResults();


                    if (albums != null && !albums.isEmpty()) {
                        String imageURL = albums.get(0).getArtworkUrl();

                        Glide.with(imageView)
                                .load(imageURL)
                                .placeholder(R.drawable.ic_launcher_background)
                                .into(imageView);
                    } else {

                        Log.e("AlbumImageRepository", "No album images found for the search query: " + albumNameWithArtist);
                        imageView.setImageResource(R.drawable.placeholder_image);
                    }
                } else {
                    Log.e("AlbumImageRepository", "Failed to fetch album images. Response code: " + response.code() + ", message: " + response.message());
                    imageView.setImageResource(R.drawable.error_image);
                }
            }


            @Override
            public void onFailure(@NonNull Call<AlbumImageModel> call, @NonNull Throwable t) {
                Log.e("Failed to retrieve album image from iTunes ", t.getMessage());
            }
        });
    }
}