package com.citytogo.jonnyhsia.rxevangelist.page.retrofit;

import com.citytogo.jonnyhsia.rxevangelist.model.RetrofitService;
import com.citytogo.jonnyhsia.rxevangelist.model.entity.Story;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by JonnyHsia on 17/10/22.
 * Retrofit Presenter
 */
class RetrofitPresenter implements RetrofitContract.Presenter {

    private RetrofitContract.View mView;
    private RetrofitService mRxService;

    private CompositeDisposable mDisposable = new CompositeDisposable();

    RetrofitPresenter(RetrofitContract.View view, RetrofitService rxService) {
        mView = view;
        mRxService = rxService;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void requestTimeline() {
        String username = "supotato";
        mView.showRequesting();

        mRxService.getTimeline(username, 0, 20, new SingleObserver<List<Story>>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable.add(d);
            }

            @Override
            public void onSuccess(List<Story> stories) {
                mView.showRequestSuccess(stories);
            }

            @Override
            public void onError(Throwable e) {
                mView.showRequestFailed(e.getMessage());
            }
        });
//        mRxService.getTimeline(username, 0, 20, new RetrofitService.OnTimelineRequestListener() {
//            @Override
//            public void onSuccess(List<Story> data) {
//                mView.showRequestSuccess(data);
//            }
//
//            @Override
//            public void onError(String message) {
//                mView.showRequestFailed(message);
//            }
//        });
    }

    @Override
    public void requestTimelineByCall() {
        String username = "supotato";
        mView.showRequesting();
        mRxService.getTimelineByCall(username, 0, 20, new RetrofitService.OnTimelineRequestListener() {
            @Override
            public void onSuccess(List<Story> data) {
                mView.showRequestSuccess(data);
            }

            @Override
            public void onError(String message) {
                mView.showRequestFailed(message);
            }
        });
    }

    @Override
    public void clearConsole() {
        mView.showConsoleCleared();
    }

    @Override
    public void requestTimelineByVolley() {
        String username = "supotato";
        mView.showRequesting();
        mRxService.getTimelineByVolley(username, 0, 20, new RetrofitService.OnTimelineVolleyRequestListener() {
            @Override
            public void onSuccess(String response) {
                mView.showLog(String.format("\n%s…", response.substring(0, 200)));
            }

            @Override
            public void onError(String message) {
                mView.showRequestFailed(message);
            }
        });
    }
}
