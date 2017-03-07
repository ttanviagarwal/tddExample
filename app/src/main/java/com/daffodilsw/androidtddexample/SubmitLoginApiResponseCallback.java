package com.daffodilsw.androidtddexample;

import android.os.Bundle;


import com.daffodilsw.api.ErrorResponse;
import com.daffodilsw.api.ResponseCallback;
import com.daffodilsw.services.authorization.login.ResLogin;

public class SubmitLoginApiResponseCallback implements ResponseCallback<ResLogin> {
    ISubmitLoginRequestApiResponseReceiver mResponseReceiver;

    SubmitLoginApiResponseCallback(ISubmitLoginRequestApiResponseReceiver submitLoginRequestApiResponseReceiver) {
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
