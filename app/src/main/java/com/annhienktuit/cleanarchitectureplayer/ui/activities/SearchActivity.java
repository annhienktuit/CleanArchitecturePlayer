package com.annhienktuit.cleanarchitectureplayer.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.widget.TextView;

import com.annhienktuit.cleanarchitectureplayer.App;
import com.annhienktuit.cleanarchitectureplayer.R;
import com.annhienktuit.cleanarchitectureplayer.di.components.DaggerPlayerComponent;
import com.annhienktuit.cleanarchitectureplayer.di.components.DaggerSearchComponent;
import com.annhienktuit.cleanarchitectureplayer.ui.presenters.SearchPresenter;

import javax.inject.Inject;

public class SearchActivity extends AppCompatActivity implements com.annhienktuit.cleanarchitectureplayer.ui.views.SearchView {

    SearchView searchView;

    TextView textViewResult;

    @Inject
    SearchPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        attachView();
        App application = (App)getApplication();
        DaggerSearchComponent
                .builder()
                .applicationComponent(application.getApplicationComponent())
                .build()
                .inject(this);
        presenter.startSearch(searchView, textViewResult);
    }

    private void attachView() {
        searchView = findViewById(R.id.searchView);
        textViewResult = findViewById(R.id.tvResult);
    }
}