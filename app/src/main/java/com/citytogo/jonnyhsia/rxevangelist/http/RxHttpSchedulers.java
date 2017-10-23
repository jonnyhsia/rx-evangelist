package com.citytogo.jonnyhsia.rxevangelist.http;

import com.citytogo.jonnyhsia.rxevangelist.helper.Kits;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.CompletableTransformer;
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
                                checkNetworkOnSubscribe(disposable);
                            }
                        });
            }
        };
    }

    public static <T> CompletableTransformer composeCompletable() {
        return new CompletableTransformer() {
            @Override
            public CompletableSource apply(Completable upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe(new Consumer<Disposable>() {
                            @Override
                            public void accept(Disposable disposable) throws Exception {
                                checkNetworkOnSubscribe(disposable);
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
                                checkNetworkOnSubscribe(disposable);
                            }
                        });
            }
        };
    }

    /**
     * 建立订阅时检查网络是否可用
     * @throws Exception 网络不可用则抛出异常
     */
    private static void checkNetworkOnSubscribe(Disposable disposable) throws Exception {
        if (!Kits.checkNetwork() && !disposable.isDisposed()) {
            disposable.dispose();
            throw new Exception("Network is unavailable");
        }
    }
}
