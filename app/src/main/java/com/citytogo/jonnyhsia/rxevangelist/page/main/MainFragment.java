package com.citytogo.jonnyhsia.rxevangelist.page.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.citytogo.jonnyhsia.rxevangelist.R;
import com.citytogo.jonnyhsia.rxevangelist.helper.Kits;
import com.citytogo.jonnyhsia.rxevangelist.model.entity.Category;
import com.citytogo.jonnyhsia.rxevangelist.page.base.BaseFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Main Fragment
 */
public class MainFragment extends BaseFragment implements MainContract.View {

    @BindView(R.id.nestedScrollView)
    RecyclerView mRecyclerCategory;

    private MainContract.Presenter mPresenter;

    private Items mItems = new Items();
    private MultiTypeAdapter mAdapter = new MultiTypeAdapter(mItems);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CategoryItemBinder binder = new CategoryItemBinder();
        binder.setOnCategoryClickListener(new CategoryItemBinder.OnCategoryClickListener() {
            @Override
            public void onClicked(int pos) {
                mPresenter.clickCategory(pos);
            }
        });
        mAdapter.register(Category.class, binder);

        int spanCount = 2;
        if (!Kits.UI.isScreenOrientationPortrait(getActivity())) {
            spanCount = 4;
        }
        mRecyclerCategory.setLayoutManager(new GridLayoutManager(getActivity(), spanCount));
        mRecyclerCategory.setHasFixedSize(true);
        mRecyclerCategory.setAdapter(mAdapter);
        mPresenter.setUpRecyclerView();
    }

    @Override
    public void initData(@NonNull List<Category> categoryList) {
        mAdapter.setItems(categoryList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void bindPresenter(@NonNull MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public MainFragment() {

    }
}
