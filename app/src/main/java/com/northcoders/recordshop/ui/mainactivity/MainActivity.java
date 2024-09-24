package com.northcoders.recordshop.ui.mainactivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import com.northcoders.recordshop.R;
import com.northcoders.recordshop.databinding.ActivityMainBinding;
import com.northcoders.recordshop.model.Album;
import com.northcoders.recordshop.ui.updateAlbum.UpdateAlbumActivity;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {

    private RecyclerView recyclerView;
    private List<Album> albumList = new ArrayList<>();
    private AlbumAdapter adapter;
    private MainActivityViewModel viewModel;
    private MainActivityClickHandler handlers;
    private ActivityMainBinding binding;
    private static final String ALBUM_KEY = "album";
    private SearchView searchViewAlbumName;
    private SearchView searchViewArtist;
    private ArrayList<Album> filteredAlbumList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_main
        );


        viewModel = new ViewModelProvider(this)
                .get(MainActivityViewModel.class);


        handlers = new MainActivityClickHandler(this);
        binding.setClickHandlers(handlers);
        getAllAlbums();

        searchViewAlbumName = findViewById(R.id.searchViewAlbumName);
        searchViewArtist = findViewById(R.id.searchViewArtist);

        searchViewAlbumName.setIconifiedByDefault(false);
        searchViewAlbumName.clearFocus();
        searchViewAlbumName.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterList(searchViewAlbumName.getQuery().toString(), searchViewArtist.getQuery().toString());
                return true;
            }
        });

        searchViewArtist.setIconifiedByDefault(false);
        searchViewArtist.clearFocus();
        searchViewArtist.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filterList(searchViewAlbumName.getQuery().toString(), searchViewArtist.getQuery().toString());
                return true;
            }
        });
    }

    private void filterList(String albumName, String artist) {
        filteredAlbumList = new ArrayList<>();
        for(Album album: albumList){
            boolean matchesAlbumName = album.getName().toLowerCase().contains(albumName.toLowerCase());
            boolean matchesArtist = album.getArtist().toLowerCase().contains(artist.toLowerCase());
            if (matchesAlbumName && matchesArtist) {
                filteredAlbumList.add(album);
            }
        }
        if(filteredAlbumList.isEmpty()){
            adapter.setFilteredList(new ArrayList<>());
            binding.noResultsTextView.setVisibility(View.VISIBLE);

        } else {
            adapter.setFilteredList(filteredAlbumList);
            binding.noResultsTextView.setVisibility(View.GONE);
        }
    }

    private void getAllAlbums() {
        viewModel.getAllAlbums().observe(this, albumsFromLiveData -> {
            albumList = albumsFromLiveData;
            displayInRecyclerView();
        });
    }

    private void displayInRecyclerView() {
        recyclerView = binding.recyclerView;
        adapter = new AlbumAdapter(this, albumList, this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter.notifyDataSetChanged();
    }



    @Override
    public void onItemClick(int position) {
        Intent i = new Intent(this, UpdateAlbumActivity.class);
        if(filteredAlbumList == null || filteredAlbumList.isEmpty()){
            i.putExtra(ALBUM_KEY, albumList.get(position));
        } else {
            i.putExtra(ALBUM_KEY, filteredAlbumList.get(position));
        }
        startActivity(i);
    }
}
