package com.citytogo.jonnyhsia.rxevangelist.page.retrofit;

import android.support.annotation.NonNull;

import com.citytogo.jonnyhsia.rxevangelist.model.entity.Story;
import com.citytogo.jonnyhsia.rxevangelist.page.base.BasePresenter;
import com.citytogo.jonnyhsia.rxevangelist.page.base.BaseView;

import java.util.List;

/**
 * Created by JonnyHsia on 17/10/22.
 * Retrofit Contract
 */
class RetrofitContract {

    interface Presenter extends BasePresenter {

        void requestTimeline();

        void clearConsole();

        void requestTimelineByVolley();
    }

    interface View extends BaseView<Presenter> {

        void showConsoleCleared();

        void showRequesting();

        void showRequestSuccess(@NonNull List<Story> storyList);

        void showRequestFailed(@NonNull String errorMsg);

        void showLog(String log);
    }
}
