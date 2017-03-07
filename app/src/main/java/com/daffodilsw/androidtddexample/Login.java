package com.daffodilsw.androidtddexample;


import android.util.Log;


import com.daffodilsw.api.ErrorCode;
import com.daffodilsw.api.ErrorMessageResolver;
import com.daffodilsw.api.ErrorResponse;
import com.daffodilsw.services.authorization.login.LoginApiRequest;
import com.daffodilsw.services.authorization.login.ReqLogin;
import com.daffodilsw.services.authorization.login.ResLogin;

import okhttp3.HttpUrl;

public class Login implements ISubmitLoginRequestApiResponseReceiver {
    private HttpUrl mBaseUrl;
    private String mEmail;
    private String mPassword;
    private LoginApiRequest mLoginApiRequest;
    private String mResponse;

    public Login(HttpUrl url){
        mBaseUrl = url;
    }

    public void tryLogin(String email, String password) {
        mEmail = email;
        mPassword = password;

        mLoginApiRequest = new LoginApiRequest();
        ErrorMessageResolver errorMessageResolver = new ErrorMessageResolver();
        errorMessageResolver.translate(ErrorCode.E_501, R.string.email_not_exist);
        mLoginApiRequest.makeRequest(new ReqLogin.Builder(email, password).build(), errorMessageResolver,
                new SubmitLoginApiResponseCallback(this), mBaseUrl);

    }

    @Override
    public void onLoginReqSubmittedSuccessfully(ResLogin data) {
        this.mResponse = "{ ";
        mResponse += ("email : " + data.getEmail());
        // mResponse += (", password : " + )
        mResponse += " }";
    }

    @Override
    public void onLoginReqSubmitFailed(ErrorResponse errorResponse) {

    }

    public String getMessage() {
        return this.mResponse;
    }
}
