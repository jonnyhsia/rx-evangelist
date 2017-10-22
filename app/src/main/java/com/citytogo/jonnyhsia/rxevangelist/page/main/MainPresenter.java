package com.citytogo.jonnyhsia.rxevangelist.page.main;

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
    public void clickCategory(int pos) {
        Class<?> cls = null;
        switch (pos) {
            case 0:
                cls = SimpleRxActivity.class;
                break;
            case 1:
                cls = TimelineActivity.class;
            default:
                break;
        }
        if (cls != null) {
            mView.jump(cls);
        }
    }
}
