package com.tanvi.api;

import android.text.TextUtils;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import timber.log.Timber;

import java.io.IOException;

/**
 * Intercepts the outgoing request and corrects the status codes in case of success and failure
 * responses and extracts the payload data to be provided as a success response.
 */
public class RequestInterceptor implements Interceptor {

    public static final String EMPTY_JSON = "{}";
    public static final int SUCCESS_RESPONSE_CODE = 200;

    @Override
    public Response intercept(Chain chain) throws IOException {

        // Proceed with the request to get the API response.
        Response response = chain.proceed(chain.request());

        // Extract the body from the finished API call.
        ResponseBody originalResponse = response.body();

        // process the original response to extract out the values.
        MediaType contentType = originalResponse.contentType();
        String responsePayload = originalResponse.string();

        // create an intercepted response similar to the one returned from server.
        ResponseBody interceptedResponse = ResponseBody.create(contentType, responsePayload);

        try {
            // Try to parse the response in the JSON format.
            JSONObject jsonObject = new JSONObject(responsePayload);

            // Extract out the documented code and message values.
            int apiCode = jsonObject.getInt("code");
            String data = jsonObject.optString("data");

            // if the data is empty, mark it as a valid empty JSON.
            if (TextUtils.isEmpty(data)) {
                data = EMPTY_JSON;
            }

            //... in case of a success response, extract the data out as a success body response.
            if (apiCode == SUCCESS_RESPONSE_CODE) {
                interceptedResponse = ResponseBody.create(contentType, data);
            }

            // Wraps the response body and assign it the proper status code,
            // ...since the API sometimes messes up the status code and the response payload code.
            response = response
                    .newBuilder()
                    .body(interceptedResponse)
                    .code(apiCode)
                    .addHeader(ServiceManager.HEADER_TOTAL_ITEMS,String.valueOf(jsonObject.optInt("total")))
                    .addHeader(ServiceManager.HEADER_TOTAL_PAGES,String.valueOf(jsonObject.optInt("total_pages")))
                    .build();

        } catch (JSONException e) {
            // log the error response.
            Timber.e("Error parsing json", e);
            // The response is not in the proper JSON format as documented so wrap
            // the response and forward it. It is not the duty of interceptor to analyse it any further.
            response = response.newBuilder().body(interceptedResponse).build();
        }

        // finally forward it.
        return response;
    }
}
