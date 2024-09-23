package com.northcoders.recordshop.service;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit = null;
    private static Retrofit imageRetrofit = null;
    private static final String BASE_URL = "http://RecordShop-env.eba-qatztzb9.eu-west-2.elasticbeanstalk.com/";
    private static final String ITUNES_BASE_URL = "https://itunes.apple.com/";

    public static AlbumApiService getService() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getOKHttpClient())
                    .build();
        }
        return retrofit.create(AlbumApiService.class);
    }

    public static AlbumImageService getImageService() {
        if (imageRetrofit == null) {
            imageRetrofit = new Retrofit.Builder()
                    .baseUrl(ITUNES_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getOKHttpClient())
                    .build();
        }
        return imageRetrofit.create(AlbumImageService.class);
    }

    private static OkHttpClient getOKHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
    }
}