package com.daffodilsw.services.authorization.login;

public enum LoginType {
    LOGIN("server");

    private String mLoginType;

    LoginType(String loginType){
        mLoginType = loginType;
    }

    public String getLoginType() {
        return mLoginType;
    }
}

