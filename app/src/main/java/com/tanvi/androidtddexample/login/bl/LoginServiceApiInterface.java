package com.tanvi.androidtddexample.login.bl;


public interface LoginServiceApiInterface {
    void submitLoginRequest(String email, String password, ISubmitLoginRequestApiResponseReceiver responseReceiver);
    void disconnect();
}
