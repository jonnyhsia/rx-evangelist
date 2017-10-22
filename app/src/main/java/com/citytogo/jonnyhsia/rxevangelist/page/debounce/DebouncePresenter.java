package com.citytogo.jonnyhsia.rxevangelist.page.debounce;

/**
 * Created by JonnyHsia on 17/10/22.
 * Debounce Presenter
 */
class DebouncePresenter implements DebounceContract.Presenter {

    private DebounceContract.View mView;
    private int clickCount = 0;

    DebouncePresenter(DebounceContract.View view) {
        mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void clearConsole() {
        clickCount = 0;
        mView.showConsoleCleared();
    }

    @Override
    public void clickDebounce() {
        mView.showValidClicked(++clickCount);
    }
}
