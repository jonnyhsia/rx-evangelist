package com.citytogo.jonnyhsia.rxevangelist.http;

import com.citytogo.jonnyhsia.rxevangelist.helper.Kits;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by JonnyHsia on 17/10/22.
 * Rx HTTP Schedulers
 */
public class RxHttpSchedulers {

    public static <T> SingleTransformer<T, T> composeSingle() {
        return new SingleTransformer<T, T>() {
            @Override
            public SingleSource<T> apply(Single<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                if (!Kits.checkNetwork() && !disposable.isDisposed()) {
                                    disposable.dispose();
                                    throw new Exception("Network is unavailable");
                                }
                            }
                        });
            }
        };
    }

    public static <T> ObservableTransformer<T, T> composeObserver() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                if (!Kits.checkNetwork() && !disposable.isDisposed()) {
                                    disposable.dispose();
                                    throw new Exception("Network is unavailable");
                                }
                            }
                        });
            }
        };
    }
}
