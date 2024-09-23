package com.northcoders.recordshop.ui.updateAlbum;


import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.northcoders.recordshop.R;
import com.northcoders.recordshop.databinding.ActivityUpateAlbumBinding;
import com.northcoders.recordshop.model.Album;
import com.northcoders.recordshop.ui.mainactivity.MainActivityViewModel;

public class UpdateAlbumActivity extends AppCompatActivity {

    private ActivityUpateAlbumBinding binding;
    private UpdateAlbumClickHandlers handlers;
    private Album album;
    private static final String ALBUM_KEY = "album";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_upate_album);

        album = getIntent().getParcelableExtra(ALBUM_KEY, Album.class);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_upate_album);

        MainActivityViewModel viewModel = new ViewModelProvider(this)
                .get(MainActivityViewModel.class);

        handlers = new UpdateAlbumClickHandlers(album, this, viewModel);
        binding.setAlbum(album);
        binding.setClickHandlers(handlers);
    }
}
