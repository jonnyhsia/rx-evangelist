package com.citytogo.jonnyhsia.rxevangelist.page.complex;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.citytogo.jonnyhsia.rxevangelist.R;
import com.citytogo.jonnyhsia.rxevangelist.model.entity.User;
import com.citytogo.jonnyhsia.rxevangelist.page.base.BaseFragment;
import com.citytogo.jonnyhsia.rxevangelist.widget.ConsoleTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Complex Fragment
 */
public class ComplexFragment extends BaseFragment implements ComplexContract.View {

    ComplexContract.Presenter mPresenter;
    @BindView(R.id.img_back)
    ImageView mImgBack;
    @BindView(R.id.tv_page_title)
    TextView mTvPageTitle;
    @BindView(R.id.tv_console)
    ConsoleTextView mTvConsole;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_complex, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        mTvPageTitle.setText(getString(R.string.page_title_complex));
        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        return view;
    }

    @Override
    public void bindPresenter(@NonNull ComplexContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public ComplexFragment() {

    }

    @OnClick(R.id.btn_clear)
    public void onClearClicked() {
        mPresenter.clearConsole();
    }

    @OnClick(R.id.btn_complex_request)
    public void onComplexRequestClicked() {
        mPresenter.complexRequest();
    }

    @Override
    public void showConsoleCleared() {
        mTvConsole.clearLog(getString(R.string.console_ready_complex));
    }

    @Override
    public void showLoginError(String message) {
        // 显示登录失败
        mTvConsole.appendLog("登录失败: " + message);
    }

    @Override
    public void showRegisterError(String message) {
        // 显示注册失败
        mTvConsole.appendLog("注册失败: " + message);
    }

    @Override
    public void showRegisterSuccess() {
        // 显示注册成功
        mTvConsole.appendLog("注册成功");
    }

    @Override
    public void showLoginSuccess(User user) {
        // 显示登录成功
        mTvConsole.appendLog("\n登录成功, " + user);
    }

    @Override
    public void showRequesting(String message) {
        // 显示 Progress Dialog 等
        mTvConsole.appendLog("\n" + message);
    }

    @Override
    public void showRequestFinished() {
        // 隐藏 ProgressDialog 等
        mTvConsole.appendLog("请求结束");
    }
}
