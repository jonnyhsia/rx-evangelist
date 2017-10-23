package com.citytogo.jonnyhsia.rxevangelist.page.complex;

import com.citytogo.jonnyhsia.rxevangelist.helper.Kits;
import com.citytogo.jonnyhsia.rxevangelist.model.RetrofitService;
import com.citytogo.jonnyhsia.rxevangelist.model.entity.User;

/**
 * Created by JonnyHsia on 17/10/23.
 * Complex Presenter
 */
class ComplexPresenter implements ComplexContract.Presenter {

    private ComplexContract.View mView;

    private RetrofitService mService;

    ComplexPresenter(ComplexContract.View view, RetrofitService service) {
        mView = view;
        mService = service;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void clearConsole() {
        mView.showConsoleCleared();
    }

    @Override
    public void complexRequest() {
        String username = Kits.generateRandomString();
        String password = "qwerty";
        String email = "test@citytogo.com";
        mView.showRequesting(String.format("尝试注册用户 %s, 密码为%s, 邮箱为%s.", username, password, email));
        mService.register(username, password, email, new RetrofitService.OnRegisterListener() {
            @Override
            public void onRegisterSuccess() {
                mView.showRegisterSuccess();
            }

            @Override
            public void onRegisterError(String message) {
                mView.showRegisterError(message);
            }

            @Override
            public void onLoginError(String message) {
                mView.showLoginError(message);
            }

            @Override
            public void onLoginSuccess(User user) {
                mView.showLoginSuccess(user);
            }

            @Override
            public void onRequestFinished() {
                mView.showRequestFinished();
            }
        });
    }
}
