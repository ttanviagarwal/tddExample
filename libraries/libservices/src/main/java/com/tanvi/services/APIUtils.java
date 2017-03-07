package com.tanvi.services;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

public class APIUtils {

    public static RequestBody createBodyWithText(String text) {
        return RequestBody.create(MediaType.parse("text/plain"), text);
    }

    public static <T> boolean cancelAPIRequest(Call<T> apiCall) {
        if (isAPICallInProgress(apiCall)) {
            apiCall.cancel();
            return true;
        }
        return false;
    }

    public static <T> boolean isAPICallInProgress(Call<T> apiCall) {
        return apiCall != null && !isAPICallExecutedOrCancelled(apiCall);
    }

    public static <T> boolean shouldDisposeAPICall(Call<T> apiCall) {
        return apiCall != null && isAPICallExecutedOrCancelled(apiCall);
    }

    private static <T> boolean isAPICallExecutedOrCancelled(Call<T> apiCall) {
        return apiCall.isExecuted() || apiCall.isCanceled();
    }

    public static void cancelAPIRequest(okhttp3.Call call) {
        if (call != null && !call.isExecuted() && !call.isCanceled()) {
            call.cancel();
        }
    }

    public static boolean isInProgress(okhttp3.Call call) {
        return call != null && (!call.isExecuted() && !call.isCanceled());
    }
}

