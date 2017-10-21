package com.citytogo.jonnyhsia.rxevangelist.page.simple;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.citytogo.jonnyhsia.rxevangelist.R;
import com.citytogo.jonnyhsia.rxevangelist.page.base.BaseFragment;

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

    private SimpleRxContract.Presenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_simple_rx, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        mTvPageTitle.setText(R.string.page_title_rx_retrofit);
        return view;
    }

    @Override
    public void bindPresenter(@NonNull SimpleRxContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public SimpleRxFragment() {

    }

    @OnClick(R.id.img_back)
    void onBackClick() {
        goBack();
    }
}
