package com.northcoders.recordshop.ui.mainactivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.databinding.DataBindingUtil;
import com.northcoders.recordshop.R;
import com.northcoders.recordshop.databinding.ActivityMainBinding;
import com.northcoders.recordshop.model.Album;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Album> albumList;
    private AlbumAdapter albumAdapter;
    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;
    private MainActivityClickHandler clickHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_main
        );


        viewModel = new ViewModelProvider(this)
                .get(MainActivityViewModel.class);

        clickHandler = new MainActivityClickHandler(this);
        binding.setClickHandler(clickHandler);

        getAllAlbums();
    }

    private void getAllAlbums() {
        viewModel.getAllAlbums().observe(this, new Observer<List<Album>>() {
            @Override
            public void onChanged(List<Album> albums) {
                albumList = (ArrayList<Album>) albums;
                displayAlbumsInRecyclerView();
            }
        });
    }

    private void displayAlbumsInRecyclerView() {
        recyclerView = binding.recyclerView;
        albumAdapter = new AlbumAdapter(albumList, this);
        recyclerView.setAdapter(albumAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        albumAdapter.notifyDataSetChanged();
    }
}
