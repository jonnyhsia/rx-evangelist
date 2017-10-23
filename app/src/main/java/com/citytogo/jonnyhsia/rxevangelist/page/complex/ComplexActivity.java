package com.citytogo.jonnyhsia.rxevangelist.page.complex;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.citytogo.jonnyhsia.rxevangelist.R;
import com.citytogo.jonnyhsia.rxevangelist.model.Injection;
import com.citytogo.jonnyhsia.rxevangelist.page.base.BaseActivity;

public class ComplexActivity extends BaseActivity {

    private ComplexPresenter mPresenter;
    private ComplexFragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_common);
        mFragment = new ComplexFragment();
        mPresenter = new ComplexPresenter(mFragment, Injection.getRetrofitService());
        mFragment.bindPresenter(mPresenter);

        replaceFragment(mFragment);
    }

    @Override
    protected void onCreateWithoutSavedInstanceState() {

    }

    @Override
    protected void onCreateHaveSavedInstanceState(@NonNull Bundle savedInstanceState) {

    }
}
