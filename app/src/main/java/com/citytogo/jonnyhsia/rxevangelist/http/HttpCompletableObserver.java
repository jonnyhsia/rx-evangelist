package com.citytogo.jonnyhsia.rxevangelist.http;

import io.reactivex.CompletableObserver;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by JonnyHsia on 17/10/23.
 * 封装 Single 类型的 HTTP 请求基本的 {@link Observer}
 */
public class HttpCompletableObserver implements CompletableObserver {

    private OnCompletableResponseCallback mOnCompletableResponseCallback;

    public HttpCompletableObserver(OnCompletableResponseCallback onCompletableResponseCallback) {
        mOnCompletableResponseCallback = onCompletableResponseCallback;
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onComplete() {
        mOnCompletableResponseCallback.onComplete();
    }

    @Override
    public void onError(Throwable e) {
        mOnCompletableResponseCallback.onError(e.getMessage());
    }
}
