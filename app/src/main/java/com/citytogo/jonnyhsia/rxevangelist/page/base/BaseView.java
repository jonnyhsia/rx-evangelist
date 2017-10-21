package com.citytogo.jonnyhsia.rxevangelist.page.base;

import android.support.annotation.NonNull;

/**
 * Created by JonnyHsia on 17/10/20.
 */

public interface BaseView<P> {

    void jump(@NonNull Class<?> cls);

    void goBack();

    void bindPresenter(@NonNull P presenter);
}
