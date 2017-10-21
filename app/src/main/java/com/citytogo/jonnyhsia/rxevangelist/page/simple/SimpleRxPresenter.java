package com.citytogo.jonnyhsia.rxevangelist.page.simple;

/**
 * Created by JonnyHsia on 17/10/22.
 * Simple Rx Presenter
 */
class SimpleRxPresenter implements SimpleRxContract.Presenter {

    private SimpleRxContract.View mView;

    public SimpleRxPresenter(SimpleRxContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {

    }
}
