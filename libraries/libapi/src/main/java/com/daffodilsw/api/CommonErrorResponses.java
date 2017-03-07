package com.daffodilsw.api;


import com.daffodilsw.androidtddexample.api.R;

public final class CommonErrorResponses {

    private CommonErrorResponses() {
    }

    public static final ErrorResponse RESPONSE_DATA_IS_NULL
            = new ErrorResponse(ErrorCode.getErrorCode(ErrorCode.E_999)
            , R.string.unable_to_receive_data);
}
