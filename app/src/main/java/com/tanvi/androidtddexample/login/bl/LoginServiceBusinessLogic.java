package com.tanvi.androidtddexample.login.bl;


import com.tanvi.androidtddexample.R;
import com.tanvi.androidtddexample.SubmitLoginApiResponseCallback;
import com.tanvi.api.ErrorCode;
import com.tanvi.api.ErrorMessageResolver;
import com.tanvi.services.authorization.login.LoginApiRequest;

import okhttp3.HttpUrl;

public class LoginServiceBusinessLogic implements LoginServiceApiInterface {
    private LoginApiRequest mLoginApiRequest;

    public LoginServiceBusinessLogic(HttpUrl url){
        mLoginApiRequest = new LoginApiRequest(url);
    }

    public LoginServiceBusinessLogic() {
        mLoginApiRequest = new LoginApiRequest();
    }

    @Override
    public void submitLoginRequest(String email, String password, ISubmitLoginRequestApiResponseReceiver responseReceiver) {
        mLoginApiRequest.cancel();
        ErrorMessageResolver errorMessageResolver = new ErrorMessageResolver();
        errorMessageResolver.translate(ErrorCode.E_501, R.string.email_not_exist);
        mLoginApiRequest.makeRequest(email, password, errorMessageResolver,
                new SubmitLoginApiResponseCallback(responseReceiver));
    }

    @Override
    public void disconnect() {
        mLoginApiRequest.cancel();
    }
}
