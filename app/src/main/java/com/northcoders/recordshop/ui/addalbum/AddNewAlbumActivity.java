package com.northcoders.recordshop.ui.addalbum;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.northcoders.recordshop.R;
import com.northcoders.recordshop.databinding.ActivityAddNewAlbumBinding;
import com.northcoders.recordshop.model.Album;
import com.northcoders.recordshop.service.AlbumApiService;
import com.northcoders.recordshop.ui.mainactivity.MainActivityViewModel;
import retrofit2.Call;
import android.content.Intent;
import android.database.Cursor;
import android.provider.OpenableColumns;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import android.app.Activity;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

public class AddNewAlbumActivity extends AppCompatActivity {

    private ActivityAddNewAlbumBinding binding;
    private AddAlbumClickHandler handler;
    private Album album;


    private ActivityResultLauncher<Intent> imagePickerLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_add_new_album);
        album = new Album();
        binding = DataBindingUtil.setContentView(
                this, R.layout.activity_add_new_album
        );

        MainActivityViewModel viewModel = new ViewModelProvider(this)
                .get(MainActivityViewModel.class);

        handler = new AddAlbumClickHandler(album, this, viewModel);

        binding.setAlbum(album);
        binding.setClickHandler(handler);

        //handler = new AddAlbumClickHandler(album, this, viewModel);

        imagePickerLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        Uri selectedImage = result.getData().getData();
                        album.setImageUrl(selectedImage.toString());
                        binding.uploadImageButton.setText("Image Selected");
                    }
                });

        handler.setImagePickerLauncher(imagePickerLauncher);
    }



}