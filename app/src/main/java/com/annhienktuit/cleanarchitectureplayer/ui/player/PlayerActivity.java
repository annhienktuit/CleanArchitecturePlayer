package com.annhienktuit.cleanarchitectureplayer.ui.player;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.annhienktuit.cleanarchitectureplayer.DefaultServiceLocator;
import com.annhienktuit.cleanarchitectureplayer.R;
import com.annhienktuit.cleanarchitectureplayer.ServiceLocator;
import com.annhienktuit.domain.usecases.GetSongUseCase;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;

public class PlayerActivity extends AppCompatActivity implements PlayerView {

    public static final String SONG_ID = "id";

    private com.google.android.exoplayer2.ui.PlayerView playerView;

    private PlayerPresenter presenter;

    private ExoPlayer exoPlayer;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        playerView = findViewById(R.id.playerView);
        ServiceLocator serviceLocator = DefaultServiceLocator.getInstance(getApplication());

        exoPlayer = new ExoPlayer.Builder(this)
                .setMediaSourceFactory(new DefaultMediaSourceFactory(this))
                .setTrackSelector(new DefaultTrackSelector(this))
                .build();

        playerView.setPlayer(exoPlayer);

        presenter = new PlayerPresenter(
                exoPlayer,
                this,
                new GetSongUseCase(serviceLocator.getSongDataSource()),
                serviceLocator.getIoExecutorService(),
                serviceLocator.getMainExecutorService());

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