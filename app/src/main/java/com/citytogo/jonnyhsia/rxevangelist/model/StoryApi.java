package com.citytogo.jonnyhsia.rxevangelist.model;

import com.citytogo.jonnyhsia.rxevangelist.model.entity.Response;
import com.citytogo.jonnyhsia.rxevangelist.model.entity.Story;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
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
     * @return 含有 Story 集合的 Response 的 Single 类型的被观察者
     */
    @GET("story/{username}/timeline")
    Single<Response<List<Story>>> getTimeline(@Path("username") String username,
                                              @Query("offset") int offset,
                                              @Query("limit") int limit);

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
