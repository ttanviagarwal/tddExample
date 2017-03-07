package com.tanvi.services.authorization.login;

import com.tanvi.api.ErrorMessageResolver;
import com.tanvi.api.ResponseCallback;
import com.tanvi.api.ResponseWrapper;
import com.tanvi.api.ServiceManager;
import com.tanvi.services.APIRequest;
import com.tanvi.services.APIUtils;
import com.tanvi.services.authorization.AuthorizationService;

import okhttp3.HttpUrl;
import retrofit2.Call;

public class LoginApiRequest implements APIRequest {
    private HttpUrl mBaseUrl;

    public LoginApiRequest() {
    }

    public LoginApiRequest(HttpUrl baseUrl) {
        this.mBaseUrl = baseUrl;
    }

    private Call<ResLogin> mLoginAPICall;

    public void makeRequest(String email, String password, ErrorMessageResolver errorMessageResolver,
                            ResponseCallback<ResLogin> responseCallback) {
        AuthorizationService authorizationService;
        if (this.mBaseUrl == null) {
            authorizationService = ServiceManager.get().createService(AuthorizationService.class);
        } else {
            authorizationService = ServiceManager.get().createService(AuthorizationService.class, this.mBaseUrl);
        }
        mLoginAPICall = authorizationService.performLogin(email, password);
        mLoginAPICall.enqueue(new ResponseWrapper<>(errorMessageResolver, responseCallback));
    }
    
    @Override
    public void cancel() {
        APIUtils.cancelAPIRequest(mLoginAPICall);
        if (APIUtils.shouldDisposeAPICall(mLoginAPICall)) {
            mLoginAPICall = null;
        }
    }


    @Override
    public boolean isInProgress() {
        return APIUtils.isAPICallInProgress(mLoginAPICall);
    }
}
