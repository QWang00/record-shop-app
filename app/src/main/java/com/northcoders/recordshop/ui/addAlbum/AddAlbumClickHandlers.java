package com.northcoders.recordshop.ui.addAlbum;



import static java.lang.String.valueOf;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.northcoders.recordshop.R;
import com.northcoders.recordshop.model.Album;
import com.northcoders.recordshop.ui.mainactivity.MainActivity;
import com.northcoders.recordshop.ui.mainactivity.MainActivityViewModel;
import java.util.Calendar;

public class AddAlbumClickHandlers {
    private Album album;
    private Context context;
    private MainActivityViewModel viewModel;
    private String selectedGenre;

    public AddAlbumClickHandlers(Album album, Context context, MainActivityViewModel viewModel) {
        this.album = album;
        this.context = context;
        this.viewModel = viewModel;
    }

    public void onSubmitBtnClick(View view) {
        if(album.getName() == null || album.getArtist() == null ||
                album.getReleaseYear()==0){
            Toast.makeText(context, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
        } else if(album.getGenre()==null){Toast.makeText(context, "Genre is empty", Toast.LENGTH_SHORT).show();

    }else{
            int currentYear = Calendar.getInstance().get(Calendar.YEAR);

            if (album.getReleaseYear() < 1900 || album.getReleaseYear() > currentYear) {
                Toast.makeText(context, "Release year must be between 1900 and " + currentYear, Toast.LENGTH_SHORT).show();
                return;
            }
            Intent i = new Intent(context, MainActivity.class);

            Album newAlbum = new Album(
                    album.getId(),
                    album.getName(),
                    album.getArtist(),
                    album.getReleaseYear(),
                    album.getGenre()

            );
            viewModel.addNewAlbum(newAlbum);
            context.startActivity(i);
        }
    }

    public void onClickBackBtn(View view) {
        Intent i = new Intent(context, MainActivity.class);
        context.startActivity(i);
    }

    public void onGenreSelected(int position) {
        String[] genres = context.getResources().getStringArray(R.array.genre_array);
        selectedGenre = genres[position];
        album.setGenre(selectedGenre);
    }


}
