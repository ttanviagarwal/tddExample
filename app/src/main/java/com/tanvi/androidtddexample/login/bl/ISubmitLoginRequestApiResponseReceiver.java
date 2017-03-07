package com.tanvi.androidtddexample.login.bl;


import com.tanvi.api.ErrorResponse;
import com.tanvi.services.authorization.login.ResLogin;

public interface ISubmitLoginRequestApiResponseReceiver {
    void onLoginReqSubmittedSuccessfully(ResLogin data);
    void onLoginReqSubmitFailed(ErrorResponse errorResponse);
}
