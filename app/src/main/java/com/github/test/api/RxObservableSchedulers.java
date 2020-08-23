package com.github.test.api;

import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import rx.Observable;

public interface RxObservableSchedulers {
    RxObservableSchedulers DEFAULT = new RxObservableSchedulers() {
        @Override
        public <T> ObservableTransformer<T, T> applySchedulers() {
            return single -> single
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    <T> ObservableTransformer<T, T> applySchedulers();
}
