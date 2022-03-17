package com.annhienktuit.cleanarchitectureplayer.ui.player;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.annhienktuit.cleanarchitectureplayer.MainThreadExecutorService;
import com.annhienktuit.cleanarchitectureplayer.R;
import com.annhienktuit.cleanarchitectureplayer.di.components.DaggerApplicationComponent;
import com.annhienktuit.cleanarchitectureplayer.di.components.ApplicationComponent;
import com.annhienktuit.cleanarchitectureplayer.di.modules.AppModule;
import com.annhienktuit.domain.interfaces.SongDataSource;
import com.annhienktuit.domain.usecases.GetSongUseCase;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;

import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

public class PlayerActivity extends AppCompatActivity implements PlayerView {

    public static final String SONG_ID = "id";

    private com.google.android.exoplayer2.ui.PlayerView playerView;

    private PlayerPresenterInterface presenter;

    private ExoPlayer exoPlayer;

    int id;

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
        setContentView(R.layout.activity_player);
        playerView = findViewById(R.id.playerView);

        ApplicationComponent applicationComponent = DaggerApplicationComponent
                .builder()
                .appModule(new AppModule(getApplication()))
                .build();
        applicationComponent.inject(this);

        exoPlayer = new ExoPlayer.Builder(this)
                .setMediaSourceFactory(new DefaultMediaSourceFactory(this))
                .setTrackSelector(new DefaultTrackSelector(this))
                .build();

        playerView.setPlayer(exoPlayer);

        presenter = new PlayerPresenter(
                exoPlayer,
                this,
                getSongUseCase,
                ioExecutorService,
                mainThreadExecutorService);

        Bundle bundle =  getIntent().getExtras();
        id = Integer.parseInt(bundle.getString(SONG_ID));
        try {
            presenter.initializeMedia(id);
            showPlayer();
        }
        catch (Exception e){
            Log.e("Nhiennha ", e.toString());
        }
    }

    @Override
    public void showPlayer() {
        playerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidePlayer() {
        playerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showErrorToast(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed(){
        presenter.releasePlayer();
        super.onBackPressed();
    }
}