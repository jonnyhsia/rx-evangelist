package com.citytogo.jonnyhsia.rxevangelist.page.base;

/**
 * Created by JonnyHsia on 17/10/20.
 */
public interface BaseView<P> {

    void jump(Class<?> cls);

    void goBack();

    void bindPresenter(P presenter);
}
