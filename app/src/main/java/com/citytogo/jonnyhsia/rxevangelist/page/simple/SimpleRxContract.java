package com.citytogo.jonnyhsia.rxevangelist.page.simple;

import com.citytogo.jonnyhsia.rxevangelist.page.base.BasePresenter;
import com.citytogo.jonnyhsia.rxevangelist.page.base.BaseView;

/**
 * Created by JonnyHsia on 17/10/22.
 * Simple Rx Contract
 */
class SimpleRxContract {

    interface Presenter extends BasePresenter {

        void clickAnimateButton();

        void clearConsole();

        void simpleJust();

        void simpleCreate();
    }

    interface View extends BaseView<Presenter> {

        void showLog(String log);

        void showAnimateLog(String log);

        void showConsoleCleared();
    }
}
