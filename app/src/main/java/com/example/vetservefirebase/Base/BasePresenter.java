package com.example.vetservefirebase.Base;

public interface BasePresenter<V extends BaseView> {
    void attachView(V view);
    void detachView();
}
