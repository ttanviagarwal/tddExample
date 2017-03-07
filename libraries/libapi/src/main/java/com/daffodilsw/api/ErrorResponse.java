package com.daffodilsw.api;

/**
 * An error implementation of the Errors returned from the server.
 */
public class ErrorResponse {

    public static final int INVALID_ERROR_CODE = -1;

    private final int mCode;
    private final int mErrorMessage;
    private String mErrorPayload;
    private Throwable mError;

    /**
     * Creates an instance of the error.
     *
     * @param code         the error code.
     * @param errorMessage the error message equivalent.
     */
    public ErrorResponse(int code, int errorMessage) {
        mCode = code;
        mErrorMessage = errorMessage;
    }

    /**
     * Gets the error.
     *
     * @return the throwable instance.
     */
    public Throwable getError() {
        return mError;
    }

    /**
     * Gets the error payload. Will be useful while debugging the error responses.
     *
     * @return the error body.
     */
    public String getErrorPayload() {
        return mErrorPayload;
    }

    /**
     * Sets the error payload.
     *
     * @param errorPayload the error body payload.
     */
    public void setErrorPayload(String errorPayload) {
        mErrorPayload = errorPayload;
    }

    /**
     * Sets the error.
     *
     * @param throwable the throwable instance.
     */
    void setError(Throwable throwable) {
        mError = throwable;
    }

    /**
     * Get the api code.
     *
     * @return the integer code.
     */
    public int getCode() {
        return mCode;
    }

    /**
     * Gets the error message.
     *
     * @return the error message.
     */
    public int getErrorMessage() {
        return mErrorMessage;
    }


    public ErrorCode getErrorCode() {
        return ErrorCode.getErrorCode(this.getCode());
    }
}
