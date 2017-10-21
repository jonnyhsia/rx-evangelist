package com.citytogo.jonnyhsia.rxevangelist.model.entity;

import android.support.annotation.DrawableRes;

/**
 * Created by JonnyHsia on 17/10/21.
 * 首页 Category 实体类
 */
public class Category {
    private String mTitle;

    private @DrawableRes
    int mForegroundRes;

    private @DrawableRes
    int mBackgroundRes;

    public Category(String title, int foregroundRes, int backgroundRes) {
        mTitle = title;
        mForegroundRes = foregroundRes;
        mBackgroundRes = backgroundRes;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public int getForegroundRes() {
        return mForegroundRes;
    }

    public void setForegroundRes(int foregroundRes) {
        mForegroundRes = foregroundRes;
    }

    public int getBackgroundRes() {
        return mBackgroundRes;
    }

    public void setBackgroundRes(int backgroundRes) {
        mBackgroundRes = backgroundRes;
    }
}
