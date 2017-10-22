package com.citytogo.jonnyhsia.rxevangelist.page.retrofit;

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
    }

    interface View extends BaseView<Presenter> {

        void showConsoleCleared();

        void showRequesting();

        void showRequestSuccess(List<Story> storyList);

        void showRequestFail(String errorMsg);
    }
}
