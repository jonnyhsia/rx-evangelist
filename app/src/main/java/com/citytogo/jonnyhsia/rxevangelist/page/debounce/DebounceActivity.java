package com.citytogo.jonnyhsia.rxevangelist.page.debounce;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.citytogo.jonnyhsia.rxevangelist.R;
import com.citytogo.jonnyhsia.rxevangelist.model.Injection;
import com.citytogo.jonnyhsia.rxevangelist.page.base.BaseActivity;

public class DebounceActivity extends BaseActivity {
    private DebouncePresenter mPresenter;
    private DebounceFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);

        mFragment = new DebounceFragment();
        mPresenter = new DebouncePresenter(mFragment);
        mFragment.bindPresenter(mPresenter);

        replaceFragment(mFragment);
    }

    @Override
    protected void onCreateWithoutSavedInstanceState() {
        mFragment = new DebounceFragment();
    }

    @Override
    protected void onCreateHaveSavedInstanceState(@NonNull Bundle savedInstanceState) {
        mFragment = (DebounceFragment) getSupportFragmentManager().findFragmentByTag(TAG_FRAG);
    }
}