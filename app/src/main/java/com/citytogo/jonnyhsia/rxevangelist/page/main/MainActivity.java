package com.citytogo.jonnyhsia.rxevangelist.page.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.citytogo.jonnyhsia.rxevangelist.R;
import com.citytogo.jonnyhsia.rxevangelist.model.Injection;
import com.citytogo.jonnyhsia.rxevangelist.page.base.BaseActivity;

/**
 * Created by JonnyHsia on 17/10/20.
 * Main Activity
 */
public class MainActivity extends BaseActivity {

    private MainPresenter mPresenter;
    private MainFragment mFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_NoTitle);
        setContentView(R.layout.activity_main);

        mPresenter = new MainPresenter(mFragment, Injection.getPageService());
        mFragment.bindPresenter(mPresenter);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, mFragment, TAG_FRAG)
                .commit();
    }

    @Override
    protected void onCreateWithoutSavedInstanceState() {
        mFragment = new MainFragment();
    }

    @Override
    protected void onCreateHaveSavedInstanceState(@NonNull Bundle savedInstanceState) {
        mFragment = (MainFragment) getSupportFragmentManager().findFragmentByTag(TAG_FRAG);
    }
}
