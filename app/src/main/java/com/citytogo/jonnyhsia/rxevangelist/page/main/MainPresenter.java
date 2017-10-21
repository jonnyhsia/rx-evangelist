package com.citytogo.jonnyhsia.rxevangelist.page.main;

/**
 * Created at 2017/10/21 0021 下午 3:56.
 */

class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;

    public MainPresenter(MainContract.View view) {
        mView = view;
        mView.bindPresenter(this);
    }

    @Override
    public void onStart() {

    }
}
