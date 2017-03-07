package com.tanvi.api;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * The Response callback invoked after the result is received and analysed.
 *
 * @param <T> the type of the response.
 */
public interface ResponseCallback<T> {

    /**
     * Invoked when we have the success result in the defined format from the APIs.
     *
     * @param data            the success response data.
     * @param additionalValue
     */
    void onSuccess(@NonNull T data, @Nullable Bundle additionalValue);

    /**
     * Invoked when we encounter any error from the APIs.
     *
     * @param errorResponse the error response with proper display message
     *                      and proper information for debugging.
     */
    void onFailure(@NonNull ErrorResponse errorResponse);

}
