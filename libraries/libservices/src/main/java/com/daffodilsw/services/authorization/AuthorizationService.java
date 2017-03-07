package com.daffodilsw.services.authorization;
import com.daffodilsw.services.authorization.login.ResLogin;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

import java.util.Map;


public interface AuthorizationService {

    String LOGIN_PATH = "login";

    @Multipart
    @POST(LOGIN_PATH)
    Call<ResLogin> performLogin(@PartMap Map<String, RequestBody> normalFields);
}
