package com.citytogo.jonnyhsia.rxevangelist.page.timeline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.citytogo.jonnyhsia.rxevangelist.Preferences;
import com.citytogo.jonnyhsia.rxevangelist.R;
import com.citytogo.jonnyhsia.rxevangelist.model.Injection;

public class TimelineActivity extends AppCompatActivity {
    private TimelinePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        String username = Preferences.getUsername(this);
        TimelineFragment fragment = new TimelineFragment();
        mPresenter = new TimelinePresenter(fragment, Injection.getStoryService(), username);
        fragment.bindPresenter(mPresenter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        mPresenter.dispose();
    }
}
