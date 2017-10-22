package com.citytogo.jonnyhsia.rxevangelist.page.retrofit;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.citytogo.jonnyhsia.rxevangelist.R;
import com.citytogo.jonnyhsia.rxevangelist.model.Injection;
import com.citytogo.jonnyhsia.rxevangelist.page.base.BaseActivity;

public class RetrofitActivity extends BaseActivity {
    private RetrofitPresenter mPresenter;
    private RetrofitFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);

        mFragment = new RetrofitFragment();
        mPresenter = new RetrofitPresenter(mFragment, Injection.getRetrofitService());
        mFragment.bindPresenter(mPresenter);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, mFragment)
                .commit();
    }

    @Override
    protected void onCreateWithoutSavedInstanceState() {
        mFragment = new RetrofitFragment();
    }

    @Override
    protected void onCreateHaveSavedInstanceState(@NonNull Bundle savedInstanceState) {
        mFragment = (RetrofitFragment) getSupportFragmentManager().findFragmentByTag(TAG_FRAG);
    }
}