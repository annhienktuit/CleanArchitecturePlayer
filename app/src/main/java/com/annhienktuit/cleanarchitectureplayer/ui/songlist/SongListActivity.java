package com.annhienktuit.cleanarchitectureplayer.ui.songlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.annhienktuit.cleanarchitectureplayer.DefaultServiceLocator;
import com.annhienktuit.cleanarchitectureplayer.R;
import com.annhienktuit.cleanarchitectureplayer.ServiceLocator;
import com.annhienktuit.cleanarchitectureplayer.ui.player.PlayerActivity;
import com.annhienktuit.domain.models.Song;
import com.annhienktuit.domain.models.SongList;
import com.annhienktuit.domain.usecases.GetSongUseCase;
import com.google.android.exoplayer2.Player;

import java.util.List;

public class SongListActivity extends AppCompatActivity implements AllSongView{

    private RecyclerView recyclerViewSongList;
    private TextView tvNoResult;
    private SongListAdapter adapter;
    private AllSongPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        attachView();
        ServiceLocator serviceLocator = DefaultServiceLocator.getInstance(getApplication());
        presenter = new AllSongPresenter(
                this,
                new GetSongUseCase(serviceLocator.getSongDataSource()),
                serviceLocator.getIoExecutorService(),
                serviceLocator.getMainExecutorService());
        adapter = new SongListAdapter(presenter);
        recyclerViewSongList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerViewSongList.setAdapter(adapter);
        presenter.loadSong();
    }

    private void attachView() {
        recyclerViewSongList = findViewById(R.id.recyclerSongList);
        tvNoResult = findViewById(R.id.tvNoResult);
    }

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
        Log.i("Nhiennha", song.getId());
        startActivity(intent);
    }

    @Override
    public void showErrorToast(String error) {
        new Handler(Looper.getMainLooper()).post(
                () -> Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show());
    }
}