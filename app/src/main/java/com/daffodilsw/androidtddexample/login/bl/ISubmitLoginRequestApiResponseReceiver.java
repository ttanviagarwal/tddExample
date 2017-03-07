package com.daffodilsw.androidtddexample.login.bl;


import com.daffodilsw.api.ErrorResponse;
import com.daffodilsw.services.authorization.login.ResLogin;

public interface ISubmitLoginRequestApiResponseReceiver {
    void onLoginReqSubmittedSuccessfully(ResLogin data);
    void onLoginReqSubmitFailed(ErrorResponse errorResponse);
}