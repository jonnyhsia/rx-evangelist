package com.citytogo.jonnyhsia.rxevangelist.page.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.citytogo.jonnyhsia.rxevangelist.R;
import com.citytogo.jonnyhsia.rxevangelist.model.Injection;

/**
 * Created by JonnyHsia on 17/10/20.
 * Main Activity
 */
public class MainActivity extends AppCompatActivity {
    private MainPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_NoTitle);
        setContentView(R.layout.activity_main);

        MainFragment fragment = new MainFragment();
        mPresenter = new MainPresenter(fragment, Injection.getPageService());

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }
}
