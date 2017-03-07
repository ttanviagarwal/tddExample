package com.tanvi.services.authorization.login;

import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.util.HashMap;
import java.util.Map;

public class ReqLogin {

    private final Map<String, RequestBody> mRequestBodyMap;

    private ReqLogin(Builder builder) {
        mRequestBodyMap = builder.mRequestBodyMap;
    }

    Map<String, RequestBody> getRequestBody() {
        return mRequestBodyMap;
    }

    public static final class Builder {

        public static final String PART_EMAIL       = "email";
        public static final String PART_PASSWORD   = "password";

        private final Map<String, RequestBody> mRequestBodyMap;

        public Builder(String email, String password) {
            mRequestBodyMap = new HashMap<>();
            mRequestBodyMap.put(PART_EMAIL, getBody(email));
            mRequestBodyMap.put(PART_PASSWORD, getBody(password));
        }

        private RequestBody getBody(String text) {
            return RequestBody.create(MediaType.parse("text/plain"), text);
        }

        public ReqLogin build() {
            return new ReqLogin(this);
        }
    }
}
