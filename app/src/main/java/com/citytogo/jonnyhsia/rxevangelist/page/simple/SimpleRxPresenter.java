package com.citytogo.jonnyhsia.rxevangelist.page.simple;

import com.citytogo.jonnyhsia.rxevangelist.model.SimpleRxService;

/**
 * Created by JonnyHsia on 17/10/22.
 * Simple Rx Presenter
 */
class SimpleRxPresenter implements SimpleRxContract.Presenter {

    private SimpleRxContract.View mView;
    private SimpleRxService mRxService;

    public SimpleRxPresenter(SimpleRxContract.View view, SimpleRxService service) {
        mView = view;
        mRxService = service;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void clickAnimateButton() {
        mView.showAnimateLog("Append a string with the typing effect.");
    }

    @Override
    public void clearConsole() {
        mView.showConsoleCleared();
    }

    @Override
    public void simpleJust() {
        mRxService.useJust(new SimpleRxService.OnSubscribeListener() {
            @Override
            public void onSubscribe() {
                mView.showLog("onSubscribe.");
            }

            @Override
            public void onNext(Integer integer) {
                mView.showLog("onNext: " + integer);
            }

            @Override
            public void onError(Throwable e) {
                mView.showLog("onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                mView.showLog("onComplete.\n");
            }
        });
    }

    @Override
    public void simpleCreate() {
        mRxService.useCreate(new SimpleRxService.OnSubscribeListener() {
            @Override
            public void onSubscribe() {
                mView.showLog("There will be a onNext(1) call after 2 seconds," +
                        " an exception will be thrown and" +
                        " the following methods [onNext(2), onComplete()] will not be called.");
            }

            @Override
            public void onNext(Integer integer) {
                mView.showLog("onNext: " + integer);
            }

            @Override
            public void onError(Throwable e) {
                mView.showLog(String.format("onError: %s\n", e.getMessage()));
            }

            @Override
            public void onComplete() {
                mView.showLog("onComplete.");
            }
        });
    }
}
