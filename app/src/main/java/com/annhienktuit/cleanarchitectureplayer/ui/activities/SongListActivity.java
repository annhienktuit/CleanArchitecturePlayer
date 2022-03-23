package com.annhienktuit.cleanarchitectureplayer.ui.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.annhienktuit.cleanarchitectureplayer.App;
import com.annhienktuit.cleanarchitectureplayer.R;
import com.annhienktuit.cleanarchitectureplayer.di.components.DaggerSongListComponent;
import com.annhienktuit.cleanarchitectureplayer.ui.adapters.SongListAdapter;
import com.annhienktuit.cleanarchitectureplayer.ui.presenters.SongListPresenter;
import com.annhienktuit.cleanarchitectureplayer.ui.views.SongListView;
import com.annhienktuit.domain.models.Song;

import java.util.List;

import javax.inject.Inject;

public class SongListActivity extends AppCompatActivity implements SongListView {

    @Inject
    SongListPresenter presenter;
    private RecyclerView recyclerViewSongList;
    private TextView tvNoResult;
    private SongListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        attachView();

        App application = (App) getApplication();
        DaggerSongListComponent
                .builder()
                .applicationComponent(application.getApplicationComponent())
                .build()
                                                                .inject(this);

        presenter.attachView(this);

        adapter = new SongListAdapter(presenter);
        recyclerViewSongList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerViewSongList.setAdapter(adapter);
        presenter.loadSong();
    }

    private void attachView() {
        recyclerViewSongList = findViewById(R.id.recyclerSongList);
        tvNoResult = findViewById(R.id.tvNoResult);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void showSongList(List<Song> songList) {
        recyclerViewSongList.setVisibility(View.VISIBLE);
        adapter.setData(songList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showNoResultText() {
        tvNoResult.setVisibility(View.VISIBLE);
    }

    @Override
    public void openSong(Song song) {
        Intent intent = new Intent(this, PlayerActivity.class);
        intent.putExtra(PlayerActivity.SONG_ID, song.getId());
        startActivity(intent);
    }

    @Override
    public void showErrorToast(String error) {
        new Handler(Looper.getMainLooper()).post(
                () -> Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show());
    }
}