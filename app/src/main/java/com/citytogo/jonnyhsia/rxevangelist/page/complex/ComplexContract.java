package com.citytogo.jonnyhsia.rxevangelist.page.complex;

import com.citytogo.jonnyhsia.rxevangelist.model.entity.User;
import com.citytogo.jonnyhsia.rxevangelist.page.base.BasePresenter;
import com.citytogo.jonnyhsia.rxevangelist.page.base.BaseView;

/**
 * Created by JonnyHsia on 17/10/23.
 * Complex Contract
 */
class ComplexContract {

    interface Presenter extends BasePresenter {

        void clearConsole();

        void complexRequest();
    }

    interface View extends BaseView<Presenter> {

        void showConsoleCleared();

        void showLoginError(String message);

        void showRegisterError(String message);

        void showRegisterSuccess();

        void showLoginSuccess(User user);

        void showRequesting(String message);

        void showRequestFinished();
    }
}
