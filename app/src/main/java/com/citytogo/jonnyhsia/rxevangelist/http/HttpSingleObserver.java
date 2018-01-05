package com.citytogo.jonnyhsia.rxevangelist.http;

import com.citytogo.jonnyhsia.rxevangelist.model.entity.Response;

import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Created by JonnyHsia on 17/10/23.
 * 封装 Single 类型的 HTTP 请求基本的 {@link Observer}
 */
public class HttpSingleObserver<T> implements SingleObserver<Response<T>> {

    private OnSingleResponseCallback<T> mSingleResponseListener;

    public HttpSingleObserver(OnSingleResponseCallback<T> singleResponseListener) {
        mSingleResponseListener = singleResponseListener;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onSuccess(Response<T> response) {
        if (response.isSuccess()) {
            mSingleResponseListener.onResponse(response.getData());
            return;
        }
        onError(new Exception(response.getError()));
    }

    @Override
    public void onError(Throwable e) {
        mSingleResponseListener.onError(e.getMessage());
    }
}
