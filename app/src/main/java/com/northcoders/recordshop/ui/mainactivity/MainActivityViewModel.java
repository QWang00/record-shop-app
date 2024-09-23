package com.northcoders.recordshop.ui.mainactivity;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import com.northcoders.recordshop.model.Album;
import com.northcoders.recordshop.model.AlbumImageModel;
import com.northcoders.recordshop.repository.AlbumRepository;

public class MainActivityViewModel extends AndroidViewModel {
    AlbumRepository albumRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.albumRepository = new AlbumRepository(application);
    }

    public LiveData<List<Album>> getAllAlbums() {
        return albumRepository.getMutableLiveData();
    }

    public void addNewAlbum(Album album) {
        albumRepository.addNewAlbum(album);
    }

    public void updateAlbum(long id, Album album) {
        albumRepository.updateAlbum(id, album);
    }

    public void deleteAlbum(long id) {
        albumRepository.deleteAlbum(id);
    }
}