package com.daffodilsw.api;

import android.os.Bundle;

import com.daffodilsw.androidtddexample.api.R;

import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

import java.io.IOException;
import java.net.ConnectException;
import java.net.UnknownHostException;

/**
 * A wrapper layer over the retrofit callback, written for distinguishing the success
 * and failure responses.
 *
 * @param <T> the class type of the success response expected.
 */
public class ResponseWrapper<T> implements Callback<T> {

    public static final String KEY_TOTAL_ITEMS = "key_total_items";
    public static final String KEY_TOTAL_PAGES = "key_total_pages";
    private final ErrorMessageResolver mErrorMessageResolver;
    private final ResponseCallback<T> mResponseCallback;

    /**
     * Creates an instance without the error mapper,
     * in case of all errors we would get the default response.
     *
     * @param responseCallback implementation of the response callback.
     */
    public ResponseWrapper(ResponseCallback<T> responseCallback) {
        mResponseCallback = responseCallback;
        mErrorMessageResolver = null;
    }

    /**
     * Creates an instance with the provided error mapper.
     *
     * @param errorMessageResolver the error message resolver.
     * @param responseCallback     implementation of the response callback.
     */
    public ResponseWrapper(ErrorMessageResolver errorMessageResolver, ResponseCallback<T> responseCallback) {
        mErrorMessageResolver = errorMessageResolver;
        mResponseCallback = responseCallback;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {

        // if the response is successful then simply forward the success response to the callbacks.
        if (response.isSuccessful()) {
            if (response.body() == null) {
                mResponseCallback.onFailure(CommonErrorResponses.RESPONSE_DATA_IS_NULL);
                return;
            } else {
                // ... provide the already parsed success response to the callback.
                Bundle bundle = new Bundle();
                if (response.headers() != null) {
                    if (response.headers().get(ServiceManager.HEADER_TOTAL_ITEMS) != null) {
                        bundle.putInt(KEY_TOTAL_ITEMS, Integer.parseInt(response.headers().get(ServiceManager.HEADER_TOTAL_ITEMS)));
                    }
                    if (response.headers().get(ServiceManager.HEADER_TOTAL_PAGES) != null) {
                        bundle.putInt(KEY_TOTAL_PAGES, Integer.parseInt(response.headers().get(ServiceManager.HEADER_TOTAL_PAGES)));
                    }
                }
                mResponseCallback.onSuccess(response.body(), bundle);
                return;
            }
        }

        String errorBodyPayload = null;

        try {
            // ... in case of error response we need to extract the error payload.
            errorBodyPayload = response.errorBody().string();

            // expecting the error payload in the defined JSON format.
            JSONObject jsonObject = new JSONObject(errorBodyPayload);
            int apiCode = jsonObject.optInt("code");
            String message = jsonObject.optString("message");

            // log the error message in the response to the console and crashlytics.
            Timber.e("Error while making request " + response.raw().request().url(), apiCode + " " + message);

            int errorMessage;
            ErrorCode errorCode = ErrorCode.getErrorCode(apiCode);

            // move forward and resolve this error if mapping exists.
            if (mErrorMessageResolver != null && errorCode != null) {
                errorMessage = mErrorMessageResolver.resolve(errorCode);
            } else {
                //.... otherwise give it a default message
                errorMessage = R.string.some_error_occurred;
            }

            // create a error response and notify the listeners.
            ErrorResponse errorResponse = new ErrorResponse(apiCode, errorMessage);
            errorResponse.setErrorPayload(errorBodyPayload);
            mResponseCallback.onFailure(errorResponse);

        } catch (JSONException | IOException e) {
            Timber.e(e, "Error while parsing json");
            ErrorResponse errorResponse = new ErrorResponse(ErrorResponse.INVALID_ERROR_CODE, R.string.some_error_occurred);
            errorResponse.setErrorPayload(errorBodyPayload);
            errorResponse.setError(e);
            mResponseCallback.onFailure(errorResponse);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable throwable) {

        // Handle the failure gracefully.
        ErrorResponse errorResponse;

        if (throwable instanceof ConnectException
                || throwable instanceof UnknownHostException) {
            // Network error
            errorResponse = new ErrorResponse(ErrorResponse.INVALID_ERROR_CODE, R.string.network_error_occurred);
        } else {
            // some more complex error occurred like conversion etc.
            errorResponse = new ErrorResponse(ErrorResponse.INVALID_ERROR_CODE, R.string.some_error_occurred);
        }

        //... set the error in the response for debugging.
        errorResponse.setError(throwable);
        // notify the callers.
        mResponseCallback.onFailure(errorResponse);
    }
}
