package com.daffodilsw.services.authorization.login;

import com.daffodilsw.api.ErrorMessageResolver;
import com.daffodilsw.api.ResponseCallback;
import com.daffodilsw.api.ResponseWrapper;
import com.daffodilsw.api.ServiceManager;
import com.daffodilsw.services.APIRequest;
import com.daffodilsw.services.APIUtils;
import com.daffodilsw.services.authorization.AuthorizationService;

import okhttp3.HttpUrl;
import retrofit2.Call;

public class LoginApiRequest implements APIRequest {

    private Call<ResLogin> mLoginAPICall;

    public void makeRequest(ReqLogin reqLogin, ErrorMessageResolver errorMessageResolver,
                            ResponseCallback<ResLogin> responseCallback, HttpUrl baseUrl) {

        AuthorizationService authorizationService = ServiceManager.get().createService(AuthorizationService.class, baseUrl);
        mLoginAPICall = authorizationService.performLogin(reqLogin.getRequestBody());
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
