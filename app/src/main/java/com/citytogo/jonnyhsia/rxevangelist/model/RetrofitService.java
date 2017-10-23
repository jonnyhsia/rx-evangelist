package com.citytogo.jonnyhsia.rxevangelist.model;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.citytogo.jonnyhsia.rxevangelist.App;
import com.citytogo.jonnyhsia.rxevangelist.http.RxHttpSchedulers;
import com.citytogo.jonnyhsia.rxevangelist.model.entity.Response;
import com.citytogo.jonnyhsia.rxevangelist.model.entity.Story;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

import static com.android.volley.Response.*;

/**
 * Created by JonnyHsia on 17/10/21.
 * Retrofit Service
 */
public class RetrofitService {

    private static final String BASE_URL_STORY = "http://139.224.238.88:8080/story/";

    private StoryApi mStoryApi;

    private CompositeDisposable mDisposable = new CompositeDisposable();

    private RequestQueue mQueue = Volley.newRequestQueue(App.getInstance());

    /**
     * 避免内存泄漏
     */
    public void dispose() {
        mDisposable.dispose();
    }

    /**
     * 获取时间线数据
     *
     * @param username 用户名
     * @param offset   页数
     * @param limit    篇数
     * @param listener 回调
     */
    public void getTimeline(String username, int offset, int limit, final OnTimelineRequestListener listener) {
        useApi().getTimeline(username, offset, limit)
                .compose(RxHttpSchedulers.<Response<List<Story>>>composeSingle())
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

    public void getTimelineByVolley(String username, int offset, int limit, final OnTimelineVolleyRequestListener listener) {
        String url = String.format("%sstory/%s/timeline?offset=%s&limit=%s", BASE_URL_STORY, username, offset, limit);
        StringRequest request = new StringRequest(Request.Method.GET, url, new Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.onSuccess(response);
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                listener.onError(volleyError.getMessage());
            }
        });
        mQueue.add(request);
    }

    public interface OnTimelineRequestListener {

        void onSuccess(List<Story> data);

        void onError(String message);
    }

    public interface OnTimelineVolleyRequestListener {

        void onSuccess(String response);

        void onError(String message);
    }
}
