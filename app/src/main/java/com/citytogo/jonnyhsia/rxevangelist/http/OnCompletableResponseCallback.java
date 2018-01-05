package com.citytogo.jonnyhsia.rxevangelist.http;

interface OnCompletableResponseCallback {

    void onComplete();

    void onError(String error);
}