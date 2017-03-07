package com.tanvi.androidtddexample;

import android.os.Bundle;


import com.tanvi.androidtddexample.login.bl.ISubmitLoginRequestApiResponseReceiver;
import com.tanvi.api.ErrorResponse;
import com.tanvi.api.ResponseCallback;
import com.tanvi.services.authorization.login.ResLogin;

public class SubmitLoginApiResponseCallback implements ResponseCallback<ResLogin> {
    ISubmitLoginRequestApiResponseReceiver mResponseReceiver;

    public SubmitLoginApiResponseCallback(ISubmitLoginRequestApiResponseReceiver submitLoginRequestApiResponseReceiver) {
        this.mResponseReceiver = submitLoginRequestApiResponseReceiver;
    }

    @Override
    public void onSuccess(ResLogin data, Bundle additionalValue) {
        if (mResponseReceiver != null) {
            mResponseReceiver.onLoginReqSubmittedSuccessfully(data);
        }
    }

    @Override
    public void onFailure(ErrorResponse errorResponse) {
        if (mResponseReceiver != null) {
            mResponseReceiver.onLoginReqSubmitFailed(errorResponse);
        }
    }
}
