package com.citytogo.jonnyhsia.rxevangelist.page.simple;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.citytogo.jonnyhsia.rxevangelist.R;
import com.citytogo.jonnyhsia.rxevangelist.page.base.BaseFragment;
import com.citytogo.jonnyhsia.rxevangelist.widget.ConsoleTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Simple Rx Fragment
 */
public class SimpleRxFragment extends BaseFragment implements SimpleRxContract.View {

    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_page_title)
    TextView mTvPageTitle;
    @BindView(R.id.tv_console)
    ConsoleTextView mTvConsole;

    private SimpleRxContract.Presenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple_rx, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        mTvPageTitle.setText(R.string.page_title_simple_rx);

        return view;
    }

    @Override
    public void bindPresenter(@NonNull SimpleRxContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @OnClick(R.id.img_back)
    void onBackClick() {
        goBack();
    }

    @OnClick({R.id.btn_clear, R.id.btn_simple_create, R.id.btn_simple_rx, R.id.btn_animate_text})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_clear:
                mPresenter.clearConsole();
                break;
            case R.id.btn_simple_create:
                mPresenter.simpleCreate();
                break;
            case R.id.btn_simple_rx:
                mPresenter.simpleJust();
                break;
            case R.id.btn_animate_text:
                mPresenter.clickAnimateButton();
                break;
        }
    }

    @Override
    public void showLog(String log) {
        mTvConsole.appendLog(log);
    }

    @Override
    public void showAnimateLog(String log) {
        mTvConsole.appendLog(log, true);
    }

    @Override
    public void showConsoleCleared() {
        mTvConsole.clearLog(getString(R.string.console_ready));
    }


    public SimpleRxFragment() {

    }
}
