package com.citytogo.jonnyhsia.rxevangelist.page.timeline;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.citytogo.jonnyhsia.rxevangelist.R;
import com.citytogo.jonnyhsia.rxevangelist.model.entity.Story;
import com.citytogo.jonnyhsia.rxevangelist.page.base.BaseFragment;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimelineFragment extends BaseFragment implements TimelineContract.View {

    private TimelineContract.Presenter mPresenter;

    public TimelineFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_timeline, container, false);
    }

    @Override
    public void bindPresenter(@NonNull TimelineContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void loadFail(@NonNull String msg) {

    }

    @Override
    public void loadSuccess(@NonNull List<Story> timelineData) {

    }

    @Override
    public void loadEmpty() {

    }
}
