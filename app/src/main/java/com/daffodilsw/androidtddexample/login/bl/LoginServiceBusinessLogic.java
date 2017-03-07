package com.daffodilsw.androidtddexample.login.bl;


import android.util.Log;


import com.daffodilsw.androidtddexample.R;
import com.daffodilsw.androidtddexample.SubmitLoginApiResponseCallback;
import com.daffodilsw.api.ErrorCode;
import com.daffodilsw.api.ErrorMessageResolver;
import com.daffodilsw.services.authorization.login.LoginApiRequest;

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
