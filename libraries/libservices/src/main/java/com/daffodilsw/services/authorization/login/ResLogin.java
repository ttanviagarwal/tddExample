package com.daffodilsw.services.authorization.login;

import com.google.gson.annotations.SerializedName;


public class ResLogin {

    private static final String FIELD_USER_ID = "user_id";
    private static final String FIELD_USERNAME = "username";
    private static final String FIELD_USER_PICTURE = "user_picture";
    private static final String FIELD_TYPE = "type";
    private static final String FIELD_EMAIL = "email";
    private static final String FIELD_MOBILE = "mobile";
    private static final String FIELD_PREF_LANGUAGE = "preferred_language";
    private static final String FIELD_IS_COMMERCIAL_USER = "is_commercial_user";

    @SerializedName(FIELD_USER_ID)
    private int mUserId;
    @SerializedName(FIELD_USERNAME)
    private String mUsername;
    @SerializedName(FIELD_USER_PICTURE)
    private String mUserPicture;
    @SerializedName(FIELD_TYPE)
    private String mType;
    @SerializedName(FIELD_EMAIL)
    private String mEmail;
    @SerializedName(FIELD_MOBILE)
    private String mMobileNumber;
    @SerializedName(FIELD_PREF_LANGUAGE)
    private String mPrefLanguage;
    @SerializedName(FIELD_IS_COMMERCIAL_USER)
    private boolean mIsCommercialUser;

    public ResLogin() {

    }

    public void setUserId(int userId) {
        mUserId = userId;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUserPicture(String userPicture) {
        mUserPicture = userPicture;
    }

    public String getUserPicture() {
        return mUserPicture;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getType() {
        return mType;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getMobileNumber() {
        return mMobileNumber;
    }

    public String getPrefLanguage() {
        return mPrefLanguage;
    }

    public boolean getIsCommercialUser() {
        return mIsCommercialUser;
    }

    public void setIsCommercialUser(boolean isCommercialUser) {
        mIsCommercialUser = isCommercialUser;
    }
}