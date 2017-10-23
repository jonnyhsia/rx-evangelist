package com.citytogo.jonnyhsia.rxevangelist.page.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.citytogo.jonnyhsia.rxevangelist.R;

/**
 * Created by JonnyHsia on 17/10/22.
 * Activity 基类
 */
@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {
    protected static final String TAG_FRAG = "fragment";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            onCreateHaveSavedInstanceState(savedInstanceState);
        } else {
            onCreateWithoutSavedInstanceState();
        }
    }

    /**
     * 当 onCreate 没有保存的实例状态时调用
     * 空的实现
     */
    protected void onCreateWithoutSavedInstanceState() {

    }

    /**
     * 当 onCreate 有保存的实例状态时调用
     * 空的实现
     */
    protected void onCreateHaveSavedInstanceState(@NonNull Bundle savedInstanceState) {

    }

    protected void replaceFragment(Fragment fragment) {
        replaceFragment(fragment, R.id.container);
    }

    protected void replaceFragment(Fragment fragment, @IdRes int containerId) {
        getSupportFragmentManager().beginTransaction()
                .replace(containerId, fragment, TAG_FRAG)
                .commit();
    }
}
