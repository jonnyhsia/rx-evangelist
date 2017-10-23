package com.citytogo.jonnyhsia.rxevangelist.page.retrofit;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.citytogo.jonnyhsia.rxevangelist.R;
import com.citytogo.jonnyhsia.rxevangelist.model.entity.Story;
import com.citytogo.jonnyhsia.rxevangelist.page.base.BaseFragment;
import com.citytogo.jonnyhsia.rxevangelist.widget.ConsoleTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class RetrofitFragment extends BaseFragment implements RetrofitContract.View {

    @BindView(R.id.tv_page_title)
    TextView mTvPageTitle;
    @BindView(R.id.tv_console)
    ConsoleTextView mTvConsole;

    RetrofitContract.Presenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_retrofit, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        mTvPageTitle.setText(R.string.page_title_rx_retrofit);

        return view;
    }

    @OnClick(R.id.img_back)
    void onBackClick() {
        goBack();
    }

    @OnClick({R.id.btn_clear, R.id.btn_request_retrofit, R.id.btn_request_okhttp, R.id.btn_request_volley})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_clear:
                mPresenter.clearConsole();
                break;
            case R.id.btn_request_retrofit:
                mPresenter.requestTimeline();
                break;
            case R.id.btn_request_okhttp:
                mPresenter.requestTimelineByCall();
                break;
            case R.id.btn_request_volley:
                mPresenter.requestTimelineByVolley();
                break;
        }
    }

    @Override
    public void bindPresenter(@NonNull RetrofitContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showConsoleCleared() {
        mTvConsole.setText(getString(R.string.console_ready));
    }

    @Override
    public void showRequesting() {
        mTvConsole.appendLog("Requesting...");
    }

    @Override
    public void showRequestSuccess(@NonNull List<Story> storyList) {
        mTvConsole.appendLog("Request Success!\n");
        // 取出至多 3 个打印到 Console 中
        int limit = Math.min(3, storyList.size());
        for (int i = 0; i < limit; i++) {
            mTvConsole.appendLog(storyList.get(i).toString() + "\n");
        }
    }

    @Override
    public void showRequestFailed(@NonNull String errorMsg) {
        mTvConsole.appendLog(String.format("Request failed.\n%s\n", errorMsg));
    }

    @Override
    public void showLog(String log) {
        mTvConsole.appendLog(log);
    }

    public RetrofitFragment() {

    }
}
