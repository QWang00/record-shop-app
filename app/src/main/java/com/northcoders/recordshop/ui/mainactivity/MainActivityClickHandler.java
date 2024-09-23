package com.northcoders.recordshop.ui.mainactivity;


import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.northcoders.recordshop.ui.addAlbum.AddNewAlbumActivity;

public class MainActivityClickHandler {

    private Context context;

    public MainActivityClickHandler(Context context) {
        this.context = context;
    }

    public void onAddNewAlbumPageBtnClicked(View view) {
        Intent i = new Intent(view.getContext(), AddNewAlbumActivity.class);
        context.startActivity(i);
    }
}