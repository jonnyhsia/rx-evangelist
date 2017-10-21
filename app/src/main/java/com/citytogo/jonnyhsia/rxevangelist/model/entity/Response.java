package com.citytogo.jonnyhsia.rxevangelist.model.entity;

/**
 * Created by JonnyHsia on 17/10/21.
 * Http Response
 */
public class Response<T> {
    private boolean success;
    private T data;
    private String error;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
