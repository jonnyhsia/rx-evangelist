package com.citytogo.jonnyhsia.rxevangelist.page.base;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import butterknife.Unbinder;

/**
 * Created by JonnyHsia on 17/10/20.
 * Fragment 基类
 */
public class BaseFragment extends Fragment {

    protected Unbinder mUnbinder;

    public void goBack() {
        getActivity().onBackPressed();
    }

    public void jump(@NonNull Class<?> cls) {
        startActivity(new Intent(getActivity(), cls));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        // 解绑
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
