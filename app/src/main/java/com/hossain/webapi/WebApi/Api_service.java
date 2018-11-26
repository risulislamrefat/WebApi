package com.hossain.webapi.WebApi;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api_service {
    @GET("/")
    Call<Example> getAllUser();
}
