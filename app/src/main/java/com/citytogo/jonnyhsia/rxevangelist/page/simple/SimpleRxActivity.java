package com.citytogo.jonnyhsia.rxevangelist.page.simple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.citytogo.jonnyhsia.rxevangelist.R;

public class SimpleRxActivity extends AppCompatActivity {
    private SimpleRxPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_rx);

        SimpleRxFragment fragment = new SimpleRxFragment();
        mPresenter = new SimpleRxPresenter(fragment);
        fragment.bindPresenter(mPresenter);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }
}
