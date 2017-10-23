package com.citytogo.jonnyhsia.rxevangelist.model;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.ArrayMap;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.citytogo.jonnyhsia.rxevangelist.App;
import com.citytogo.jonnyhsia.rxevangelist.http.RxHttpSchedulers;
import com.citytogo.jonnyhsia.rxevangelist.model.entity.Response;
import com.citytogo.jonnyhsia.rxevangelist.model.entity.Story;
import com.citytogo.jonnyhsia.rxevangelist.model.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;

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

    public void getTimelineByCall(String username, int offset, int limit, final OnTimelineRequestListener listener) {
        useApi().getTimelineByCall(username, offset, limit).enqueue(new Callback<Response<List<Story>>>() {
            @Override
            public void onResponse(@NonNull Call<Response<List<Story>>> call, @NonNull retrofit2.Response<Response<List<Story>>> response) {
                Response<List<Story>> timelineResponse = response.body();

                if (timelineResponse == null) {
                    return;
                }
                if (!timelineResponse.isSuccess()) {
                    listener.onError(timelineResponse.getError());
                    return;
                }

                listener.onSuccess(timelineResponse.getData());
            }

            @Override
            public void onFailure(@NonNull Call<Response<List<Story>>> call, @NonNull Throwable t) {
                listener.onError(t.getMessage());
            }
        });
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

    /**
     * 注册用户并登录接口
     *
     * @param username 用户名
     * @param password 密码
     * @param email    邮箱
     * @param listener 注册与登录的回调
     */
    public void register(final String username, String password, String email, final OnRegisterListener listener) {
        Map<String, String> params = new ArrayMap<>();
        params.put("username", username);
        params.put("password", password);
        params.put("email", email);

        useApi().register(params)
                .compose(RxHttpSchedulers.composeCompletable())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        listener.onRequestFinished();
                        listener.onRegisterError(throwable.getMessage());
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        listener.onRegisterSuccess();
                    }
                })
                // 完成后调用登录接口
                .observeOn(Schedulers.io())
                .andThen(useApi().login(username, password))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Response<User>>() {
                    @Override
                    public void accept(Response<User> userResponse) throws Exception {
                        listener.onRequestFinished();
                        if (!userResponse.isSuccess()) {
                            listener.onLoginError(userResponse.getError());
                            return;
                        }
                        listener.onLoginSuccess(userResponse.getData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        listener.onRequestFinished();
                        listener.onLoginError(throwable.getMessage());
                    }
                });
    }

    private StoryApi useApi() {
        if (mStoryApi == null) {
            mStoryApi = Injection.getRetrofitInstance(BASE_URL_STORY).create(StoryApi.class);
        }
        return mStoryApi;
    }

    public interface OnTimelineRequestListener {

        void onSuccess(List<Story> data);

        void onError(String message);
    }

    public interface OnTimelineVolleyRequestListener {

        void onSuccess(String response);

        void onError(String message);
    }

    public interface OnRegisterListener {

        /**
         * 注册成功
         */
        void onRegisterSuccess();

        /**
         * 注册失败
         *
         * @param message 注册错误信息
         */
        void onRegisterError(String message);

        /**
         * 登录失败
         *
         * @param message 登录错误信息
         */
        void onLoginError(String message);

        /**
         * 登录成功
         *
         * @param user 用户信息
         */
        void onLoginSuccess(User user);

        /**
         * 请求结束
         */
        void onRequestFinished();
    }
}
