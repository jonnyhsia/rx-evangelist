package com.citytogo.jonnyhsia.rxevangelist.page.simple;

import com.citytogo.jonnyhsia.rxevangelist.page.base.BasePresenter;
import com.citytogo.jonnyhsia.rxevangelist.page.base.BaseView;

/**
 * Created by JonnyHsia on 17/10/22.
 * Simple Rx Contract
 */
public class SimpleRxContract {

    interface Presenter extends BasePresenter {

    }

    interface View extends BaseView<Presenter> {

    }
}
