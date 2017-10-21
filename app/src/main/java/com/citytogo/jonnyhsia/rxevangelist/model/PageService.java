package com.citytogo.jonnyhsia.rxevangelist.model;

import android.support.annotation.IdRes;
import android.support.annotation.StringRes;

import com.citytogo.jonnyhsia.rxevangelist.App;
import com.citytogo.jonnyhsia.rxevangelist.R;
import com.citytogo.jonnyhsia.rxevangelist.model.entity.Category;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by JonnyHsia on 17/10/21.
 * Page Service
 */
public class PageService {

    /**
     * @return 返回首页的展示的 Category 集合
     */
    public List<Category> getMainPageCategories() {
        String[] titles = App.getInstance().getResources().getStringArray(R.array.categories);
        int[] foregrounds = new int[]{
                R.mipmap.rx_red, R.mipmap.rx_orange, R.mipmap.rx_green,
                R.mipmap.rx_blue, R.mipmap.rx_purple, R.mipmap.rx_kt};
        int[] backgrounds = new int[]{
                R.drawable.shape_red, R.drawable.shape_orange, R.drawable.shape_green,
                R.drawable.shape_blue, R.drawable.shape_purple, R.drawable.shape_grey};

        List<Category> categoryList = new ArrayList<>(titles.length);
        for (int i = 0; i < titles.length; i++) {
            categoryList.add(new Category(titles[i], foregrounds[i], backgrounds[i]));
        }
        return categoryList;
    }
}
