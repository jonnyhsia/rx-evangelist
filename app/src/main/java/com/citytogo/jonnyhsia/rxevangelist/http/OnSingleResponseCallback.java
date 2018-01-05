package com.citytogo.jonnyhsia.rxevangelist.http;

interface OnSingleResponseCallback<T> {

    void onResponse(T data);

    void onError(String error);
}