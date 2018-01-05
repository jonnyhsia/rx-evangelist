package com.citytogo.jonnyhsia.rxevangelist.http;

import com.citytogo.jonnyhsia.rxevangelist.model.entity.Response;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.functions.Function;

/**
 * Created by JonnyHsia on 17/10/25.
 * 封装对 Api 请求的统一处理
 */
public class RxHttpHandler {

    public static <T> ObservableTransformer<Response<T>, T> handleObservable() {
        return new ObservableTransformer<Response<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<Response<T>> upstream) {
                return upstream.flatMap(new Function<Response<T>, ObservableSource<T>>() {
                    @Override
                    public ObservableSource<T> apply(final Response<T> response) throws Exception {
                        if (!response.isSuccess()) {
                            return Observable.error(new Exception("请求失败: " + response.getError()));
                        }
                        if (response.getData() == null) {
                            return Observable.error(new Exception("网络异常"));
                        }
                        return Observable.create(new ObservableOnSubscribe<T>() {
                            @Override
                            public void subscribe(ObservableEmitter<T> e) throws Exception {
                                e.onNext(response.getData());
                            }
                        });
                    }
                });
            }
        };
    }

    public static <T> SingleTransformer<Response<T>, T> handleSingle() {
        return new SingleTransformer<Response<T>, T>() {
            @Override
            public SingleSource<T> apply(Single<Response<T>> upstream) {
                // 通过 flatMap 将 Response<T> 类型的 Single 转换成 T 类型 SingleSource
                return upstream.flatMap(new Function<Response<T>, SingleSource<T>>() {
                    @Override
                    public SingleSource<T> apply(final Response<T> response) {
                        // 处理 response 的各个状态，返回各类的错误到下游
                        if (!response.isSuccess()) {
                            return Single.error(new Exception(
                                    "请求失败: " + response.getError()));
                        }
                        if (response.getData() == null) {
                            return Single.error(new Exception("网络异常"));
                        }
                        // 若无误，则创建一个 Single 传递到下游
                        return Single.just(response.getData());
                    }
                });
            }
        };
    }



}