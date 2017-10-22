package com.citytogo.jonnyhsia.rxevangelist.page.debounce;

import com.citytogo.jonnyhsia.rxevangelist.model.entity.Story;
import com.citytogo.jonnyhsia.rxevangelist.page.base.BasePresenter;
import com.citytogo.jonnyhsia.rxevangelist.page.base.BaseView;

import java.util.List;

/**
 * Created by JonnyHsia on 17/10/22.
 * Retrofit Contract
 */
class DebounceContract {

    interface Presenter extends BasePresenter {

        void clearConsole();

        void clickDebounce();
    }

    interface View extends BaseView<Presenter> {

        void showConsoleCleared();

        void showValidClicked(int cnt);
    }
}
