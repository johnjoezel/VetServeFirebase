package com.example.vetservefirebase.SignIn;

import com.example.vetservefirebase.Base.BaseView;

public interface SignInView extends BaseView {
    void showValidationError(String message);
    void loginSuccess();
    void seterror(String errorcode, String message);
    void isLogin(boolean isLogin);
}
