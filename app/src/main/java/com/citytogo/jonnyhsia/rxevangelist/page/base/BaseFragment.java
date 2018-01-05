package com.citytogo.jonnyhsia.rxevangelist.page.base;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.citytogo.jonnyhsia.rxevangelist.App;
import com.citytogo.jonnyhsia.rxevangelist.helper.ToastUtil;

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

    public void showMessage(String message) {
        ToastUtil.showToast(App.getInstance(), message);
    }

    protected <T> T checkNull(@Nullable T t) {
        if (t != null) {
            return t;
        } else {
            throw new NullPointerException("A null value is checked.");
        }
    }

    public boolean isActive() {
        return mUnbinder != null;
    }
}
