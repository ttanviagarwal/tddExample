package com.daffodilsw.androidtddexample.login.bl;


public interface LoginServiceApiInterface {
    void submitLoginRequest(String email, String password, ISubmitLoginRequestApiResponseReceiver responseReceiver);
    void disconnect();
}
