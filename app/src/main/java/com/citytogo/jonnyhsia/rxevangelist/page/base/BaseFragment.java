package com.citytogo.jonnyhsia.rxevangelist.page.base;

import android.content.Intent;
import android.support.v4.app.Fragment;

/**
 * Created by JonnyHsia on 17/10/20.
 */
public class BaseFragment extends Fragment {

    void goBack() {
        getActivity().onBackPressed();
    }

    void jump(Class<?> cls) {
        startActivity(new Intent(getActivity(), cls));
    }

}
