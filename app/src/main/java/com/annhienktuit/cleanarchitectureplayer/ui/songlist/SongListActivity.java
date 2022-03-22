package com.annhienktuit.cleanarchitectureplayer.ui.songlist;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.annhienktuit.cleanarchitectureplayer.MainThreadExecutorService;
import com.annhienktuit.cleanarchitectureplayer.R;
import com.annhienktuit.cleanarchitectureplayer.di.components.ApplicationComponent;
import com.annhienktuit.cleanarchitectureplayer.di.components.DaggerApplicationComponent;
import com.annhienktuit.cleanarchitectureplayer.di.modules.AppModule;
import com.annhienktuit.cleanarchitectureplayer.ui.player.PlayerActivity;
import com.annhienktuit.domain.interfaces.SongDataSource;
import com.annhienktuit.domain.models.Song;
import com.annhienktuit.domain.usecases.GetSongUseCase;

import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

public class SongListActivity extends AppCompatActivity implements SongListView {

    private RecyclerView recyclerViewSongList;
    private TextView tvNoResult;
    private SongListAdapter adapter;
    private SongListPresenter presenter;

    @Inject
    MainThreadExecutorService mainThreadExecutorService;

    @Inject
    ExecutorService ioExecutorService;

    @Inject
    SongDataSource onlineSongDataSource;

    @Inject
    GetSongUseCase getSongUseCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        attachView();

        ApplicationComponent applicationComponent = DaggerApplicationComponent
                .builder()
                .appModule(new AppModule(getApplication()))
                .build();

        applicationComponent.inject(this);

        presenter = new SongListPresenterImpl(
                this,
                getSongUseCase,
                ioExecutorService,
                mainThreadExecutorService);
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
        mainThreadExecutorService.execute(() -> Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show());
    }
}