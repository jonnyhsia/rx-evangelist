package com.citytogo.jonnyhsia.rxevangelist.page.debounce;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.citytogo.jonnyhsia.rxevangelist.R;
import com.citytogo.jonnyhsia.rxevangelist.page.base.BaseFragment;
import com.citytogo.jonnyhsia.rxevangelist.widget.ConsoleTextView;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * A simple {@link Fragment} subclass.
 */
public class DebounceFragment extends BaseFragment implements DebounceContract.View {

    @BindView(R.id.tv_page_title)
    TextView mTvPageTitle;
    @BindView(R.id.tv_console)
    ConsoleTextView mTvConsole;
    @BindView(R.id.btn_debounce)
    Button mBtnDebounce;

    DebounceContract.Presenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_debounce, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        mTvConsole.setDebug(false);
        mTvPageTitle.setText(R.string.page_title_debouce);

        RxView.clicks(mBtnDebounce)
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        checkNull(mPresenter).clickDebounce();
                    }
                });

        return view;
    }

    @OnClick(R.id.img_back)
    void onBackClick() {
        goBack();
    }

    @OnClick({R.id.btn_clear, R.id.btn_debounce})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_clear:
                mPresenter.clearConsole();
                break;
            case R.id.btn_debounce:
                mPresenter.clickDebounce();
                break;
        }
    }

    @Override
    public void bindPresenter(@NonNull DebounceContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showConsoleCleared() {
        mTvConsole.setText(getString(R.string.console_ready_debouce));
    }

    @Override
    public void showValidClicked(int cnt) {
        mTvConsole.appendLog(String.format(Locale.CHINA, "A valid click (%d)", cnt));
    }

    public DebounceFragment() {

    }
}
