package com.example.facebooktest.api;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public interface RxSingleSchedulers {
    RxSingleSchedulers DEFAULT = new RxSingleSchedulers() {
        @Override
        public <T> ObservableTransformer<T, T> applySchedulers() {
            return single -> single
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    <T> ObservableTransformer<T, T> applySchedulers();
}
