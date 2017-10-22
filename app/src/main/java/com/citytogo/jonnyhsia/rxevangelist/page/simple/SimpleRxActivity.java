package com.citytogo.jonnyhsia.rxevangelist.page.simple;

import android.support.annotation.NonNull;
import android.os.Bundle;

import com.citytogo.jonnyhsia.rxevangelist.R;
import com.citytogo.jonnyhsia.rxevangelist.page.base.BaseActivity;

public class SimpleRxActivity extends BaseActivity {
    private SimpleRxPresenter mPresenter;
    private SimpleRxFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);

        mFragment = new SimpleRxFragment();
        mPresenter = new SimpleRxPresenter(mFragment);
        mFragment.bindPresenter(mPresenter);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, mFragment)
                .commit();
    }

    @Override
    protected void onCreateWithoutSavedInstanceState() {
        mFragment = new SimpleRxFragment();
    }

    @Override
    protected void onCreateHaveSavedInstanceState(@NonNull Bundle savedInstanceState) {
        mFragment = (SimpleRxFragment) getSupportFragmentManager().findFragmentByTag(TAG_FRAG);
    }
}