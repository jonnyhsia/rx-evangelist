package com.citytogo.jonnyhsia.rxevangelist.page.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.citytogo.jonnyhsia.rxevangelist.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme_NoTitle);
        setContentView(R.layout.activity_main);

        MainFragment fragment = new MainFragment();
        MainPresenter presenter = new MainPresenter(fragment);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }
}