package com.citytogo.jonnyhsia.rxevangelist.page.main;

import com.citytogo.jonnyhsia.rxevangelist.model.PageService;
import com.citytogo.jonnyhsia.rxevangelist.model.entity.Category;

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
        mView.bindPresenter(this);
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
        // TODO: 17/10/21
    }
}
