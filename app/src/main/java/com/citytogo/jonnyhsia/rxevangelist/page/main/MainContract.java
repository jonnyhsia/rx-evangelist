package com.citytogo.jonnyhsia.rxevangelist.page.main;

import android.support.annotation.NonNull;

import com.citytogo.jonnyhsia.rxevangelist.model.entity.Category;
import com.citytogo.jonnyhsia.rxevangelist.page.base.BasePresenter;
import com.citytogo.jonnyhsia.rxevangelist.page.base.BaseView;

import java.util.List;

/**
 * Created by JonnyHsia on 17/10/20.
 * Main Contract
 */
public class MainContract {

    interface View extends BaseView<Presenter> {

        void initData(@NonNull List<Category> categoryList);
    }

    interface Presenter extends BasePresenter {

        void setUpRecyclerView();

        void clickCategory(@NonNull Class<?> pos);
    }
}
