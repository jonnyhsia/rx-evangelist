package com.citytogo.jonnyhsia.rxevangelist.model;


import com.citytogo.jonnyhsia.rxevangelist.model.entity.Response;
import com.citytogo.jonnyhsia.rxevangelist.model.entity.Story;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by JonnyHsia on 17/10/21.
 */

public class StoryService {

    private static final String BASE_URL_STORY = "http://139.224.238.88:8080/story/";

    private StoryApi mStoryApi;

    private CompositeDisposable mDisposable = new CompositeDisposable();

    public void getTimeline(String username, int offset, int limit, final OnTimelineRequestListener listener) {
        useApi().getTimeline(username, offset, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response<List<Story>>>() {
                    @Override
                    public void accept(Response<List<Story>> response) throws Exception {
                        if (response.isSuccess()) {
                            listener.onSuccess(response.getData());
                        } else {
                            listener.onError(response.getError());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        listener.onError(throwable.getMessage());
                    }
                });
    }

    private StoryApi useApi() {
        if (mStoryApi == null) {
            mStoryApi = Injection.getRetrofitInstance(BASE_URL_STORY).create(StoryApi.class);
        }
        return mStoryApi;
    }

    /**
     * 避免内存泄漏
     */
    public void dispose() {
        mDisposable.dispose();
    }

    public interface OnTimelineRequestListener {

        void onSuccess(List<Story> data);

        void onError(String message);
    }
}
