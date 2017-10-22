package com.citytogo.jonnyhsia.rxevangelist.page.main;

import android.support.annotation.NonNull;

import com.citytogo.jonnyhsia.rxevangelist.model.PageService;
import com.citytogo.jonnyhsia.rxevangelist.model.entity.Category;
import com.citytogo.jonnyhsia.rxevangelist.page.simple.SimpleRxActivity;
import com.citytogo.jonnyhsia.rxevangelist.page.timeline.TimelineActivity;

import java.util.List;

/**
 * Created by JonnyHsia on 17/10/20.
 * Main Presenter
 */
class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;
    private PageService mPageService;

    MainPresenter(MainContract.View view, PageService pageService) {
        mView = view;
        mPageService = pageService;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void setUpRecyclerView() {
        List<Category> categoryList = mPageService.getMainPageCategories();
        mView.initData(categoryList);
    }

    @Override
    public void clickCategory(@NonNull Class<?> cls) {
        mView.jump(cls);
    }
}
