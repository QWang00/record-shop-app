package com.northcoders.recordshop.ui.updateAlbum;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Objects;

import com.northcoders.recordshop.R;
import com.northcoders.recordshop.model.Album;

import com.northcoders.recordshop.ui.mainactivity.MainActivity;
import com.northcoders.recordshop.ui.mainactivity.MainActivityViewModel;

public class UpdateAlbumClickHandlers {

    private Album album;
    private Context context;
    private MainActivityViewModel viewModel;
    private long albumId;
    private String selectedGenre;

    public UpdateAlbumClickHandlers(Album album, Context context, MainActivityViewModel viewModel) {
        this.album = album;
        this.context = context;
        this.viewModel = viewModel;
    }

    public void onSubmitBtnClick(View view) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        Album updatedAlbum = new Album(
                album.getId(),
                album.getName(),
                album.getArtist(),
                album.getReleaseYear(),
                album.getGenre()

        );

        if (Objects.equals(updatedAlbum.getName(), "") ||
                Objects.equals(updatedAlbum.getArtist(), "") ||
                Objects.equals(updatedAlbum.getReleaseYear(), "") ||
                Objects.equals(updatedAlbum.getGenre(), "")) {
            Toast.makeText(context, "Fields cannot be empty", Toast.LENGTH_LONG).show();

        } else if (updatedAlbum.getReleaseYear() < 1900 || updatedAlbum.getReleaseYear() > currentYear) {
                Toast.makeText(context, "Release year must be between 1900 and " + currentYear, Toast.LENGTH_LONG).show();
        } else {
            Intent i = new Intent(context, MainActivity.class);
            albumId = album.getId();
            viewModel.updateAlbum(albumId, updatedAlbum);
            context.startActivity(i);
        }

    }

    public void onDeleteBtnClicked(View view) {
        Intent i = new Intent(context, MainActivity.class);

        albumId = album.getId();

        viewModel.deleteAlbum(albumId);

        context.startActivity(i);

    }

    public void onBackButtonClicked(View view) {
        Intent i = new Intent(context, MainActivity.class);
        context.startActivity(i);
    }

    public void onGenreSelected(int position) {
        String[] genres = context.getResources().getStringArray(R.array.genre_array);
        selectedGenre = genres[position];
        album.setGenre(selectedGenre);
    }
}