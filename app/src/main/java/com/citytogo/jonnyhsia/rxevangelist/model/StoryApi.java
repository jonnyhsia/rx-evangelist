package com.citytogo.jonnyhsia.rxevangelist.model;

import com.citytogo.jonnyhsia.rxevangelist.model.entity.Response;
import com.citytogo.jonnyhsia.rxevangelist.model.entity.Story;
import com.citytogo.jonnyhsia.rxevangelist.model.entity.User;

import java.util.List;
import java.util.Map;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by JonnyHsia on 17/10/20.
 * Story Api
 */
public interface StoryApi {

    /**
     * 获取用户时间线上的故事
     *
     * @param username 用户名
     * @param offset   时间线页数
     * @param limit    每页获取的故事篇数
     */
    @GET("story/{username}/timeline")
    Observable<Response<List<Story>>> getTimeline(@Path("username") String username,
                                                  @Query("offset") int offset,
                                                  @Query("limit") int limit);

    @GET("story/{username}/timeline")
    Single<Response<List<Story>>> getStoryTimeline(@Path("username") String username,
                                                   @Query("offset") int offset,
                                                   @Query("limit") int limit);

    @POST("user/register")
    @FormUrlEncoded
    Completable register(@FieldMap Map<String, String> user);

    @POST("user/login")
    @FormUrlEncoded
    Single<Response<User>> login(@Field("username") String username,
                                 @Field("password") String password);

    /**
     * 获取用户时间线上的故事
     *
     * @param username 用户名
     * @param offset   时间线页数
     * @param limit    每页获取的故事篇数
     * @return 含有 Story 集合的 Response 的 Single 类型的被观察者
     */
    @GET("story/{username}/timeline")
    Call<Response<List<Story>>> getTimelineByCall(@Path("username") String username,
                                                  @Query("offset") int offset,
                                                  @Query("limit") int limit);
}
