package com.tanvi.services.authorization;
import com.tanvi.services.authorization.login.ResLogin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface AuthorizationService {

    String LOGIN_PATH = "login";

    @FormUrlEncoded
    @POST(LOGIN_PATH)
    Call<ResLogin> performLogin(@Field("email") String email, @Field("password") String password);
}
