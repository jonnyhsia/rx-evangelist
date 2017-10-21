package com.citytogo.jonnyhsia.rxevangelist.page.timeline;

import android.support.annotation.NonNull;

import com.citytogo.jonnyhsia.rxevangelist.model.entity.Story;
import com.citytogo.jonnyhsia.rxevangelist.page.base.BasePresenter;
import com.citytogo.jonnyhsia.rxevangelist.page.base.BaseView;

import java.util.List;

/**
 * Created by JonnyHsia on 17/10/21.
 */

public class TimelineContract {

    interface View extends BaseView<Presenter> {

        void loadFail(@NonNull String msg);

        void loadSuccess(@NonNull List<Story> timelineData);

        void loadEmpty();
    }

    interface Presenter extends BasePresenter {

        void requestTimeline();

        void dispose();
    }
}