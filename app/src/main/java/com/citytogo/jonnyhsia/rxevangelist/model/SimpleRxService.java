package com.citytogo.jonnyhsia.rxevangelist.model;

import android.support.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by JonnyHsia on 17/10/22.
 * Simple Rx Service
 */
public class SimpleRxService {

    public void useJust(@NonNull final OnSubscribeListener listener) {
        Observable.just(0, 1, 2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        listener.onSubscribe();
                    }

                    @Override
                    public void onNext(Integer integer) {
                        listener.onNext(integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        listener.onComplete();
                    }
                });
    }

    public void useCreate(@NonNull final OnSubscribeListener listener) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Thread.sleep(2000);
                e.onNext(1);
                Thread.sleep(16);
                e.onError(new Exception("Throw an exception initiative."));
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        listener.onSubscribe();
                    }

                    @Override
                    public void onNext(Integer integer) {
                        listener.onNext(integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        listener.onComplete();
                    }
                });
    }

    public interface OnSubscribeListener {
        void onSubscribe();

        void onNext(Integer integer);

        void onError(Throwable e);

        void onComplete();
    }
}
