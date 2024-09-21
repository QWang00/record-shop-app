package com.northcoders.recordshop.ui.addalbum;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;


import com.northcoders.recordshop.model.Album;
import com.northcoders.recordshop.ui.mainactivity.MainActivity;
import com.northcoders.recordshop.ui.mainactivity.MainActivityViewModel;

import java.util.Calendar;


public class AddAlbumClickHandler {
    private Album album;
    private Context context;
    private MainActivityViewModel viewModel;

    private ActivityResultLauncher<Intent> imagePickerLauncher; // TODO: gpt extra

    public AddAlbumClickHandler(Album album, Context context, MainActivityViewModel viewModel) {
        this.album = album;
        this.context = context;
        this.viewModel = viewModel;

    }

    
    public void onAddBtnClicked(View view){
        if(album.getArtist() == null ||
                album.getName()==null ||
                album.getGenre()== null ||
                album.getReleaseYear()==0 ||
                album.getImageUrl()==null) {

            Toast.makeText(context, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
        } else {
                int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                if (album.getReleaseYear() < 1900 || album.getReleaseYear() > currentYear) {
                    Toast.makeText(context, "Release year must be between 1900 and " + currentYear, Toast.LENGTH_SHORT).show();
                    return;
                }

                Album newAlbum = new Album(
                        album.getArtist(),
                        album.getGenre(),
                        album.getId(),
                        album.getName(),
                        album.getReleaseYear(),
                        album.getImageUrl()
                );
                viewModel.addAlbum(newAlbum);

                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }
    }

    public void onCancelButtonClicked(View view){
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }


    public void onUploadImageButtonClicked(View view) {
        if (imagePickerLauncher != null) {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            String[] mimeTypes = {"image/jpeg", "image/png"};
            intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
            imagePickerLauncher.launch(intent);
        } else {
            Toast.makeText(context, "Image picker not initialized", Toast.LENGTH_SHORT).show();
        }
    }


    //TODO: gpt extra method setImagePickerLaucher
    public void setImagePickerLauncher(ActivityResultLauncher<Intent> imagePickerLauncher) {
        this.imagePickerLauncher = imagePickerLauncher;
    }



}
