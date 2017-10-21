package com.citytogo.jonnyhsia.rxevangelist.page.main;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.citytogo.jonnyhsia.rxevangelist.R;
import com.citytogo.jonnyhsia.rxevangelist.page.base.BaseFragment;

public class MainFragment extends BaseFragment implements MainContract.View{

    private MainContract.Presenter mPresenter;

    public MainFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void bindPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
