package com.annhienktuit.cleanarchitectureplayer.ui.presenters;

import android.widget.TextView;

import com.annhienktuit.cleanarchitectureplayer.ui.views.SearchView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Created by Nhien Nguyen on 3/30/2022
 */
public class SearchPresenterImpl implements SearchPresenter{

    private SearchView searchView;

    @Inject
    public SearchPresenterImpl(){}

    @Override
    public void startSearch(androidx.appcompat.widget.SearchView searchView, TextView textView) {
        SearchObservable.fromView(searchView)
                .debounce(300, TimeUnit.MILLISECONDS)
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(String s) throws Throwable {
                        if(s.isEmpty()){
                            textView.setText("Null");
                            return false;
                        }
                        else {
                            return true;
                        }
                    }
                })
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(textView::setText);
    }
}
