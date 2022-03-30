package com.annhienktuit.cleanarchitectureplayer.ui.presenters;

import androidx.appcompat.widget.SearchView;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;


/**
 * Created by Nhien Nguyen on 3/30/2022
 */
public class SearchObservable {

    public static Observable<String> fromView(SearchView searchView){

        final PublishSubject<String> subject = PublishSubject.create();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){

            @Override
            public boolean onQueryTextSubmit(String query) {
                subject.onComplete();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                subject.onNext(newText);
                return false;
            }
        });

        return subject;
    }

}
