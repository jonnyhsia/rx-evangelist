package com.citytogo.jonnyhsia.rxevangelist.page;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by JonnyHsia on 17/10/22.
 */

public class Test {
    private Disposable mDisposable;

    void append(CharSequence charSequence) {
    }

    void xxx(final CharSequence log) {
        int period = 200;
        final int[] pos = {0};
        int length = log.length();

        Observable.interval(period, TimeUnit.MILLISECONDS, Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        // observable 被订阅时
                    }
                    @Override
                    public void onNext(Long aLong) {
                        // 每个间隔把字符逐个地取出并添加到 TextView 中
                        // 判断是否读取完成, 已完成则结束整个订阅
                    }
                    @Override
                    public void onError(Throwable e) {
                        // 异常处理
                    }
                    @Override
                    public void onComplete() {
                        // observable 结束订阅时
                    }
                });
    }
}
