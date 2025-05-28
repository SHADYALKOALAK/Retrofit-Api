package com.shadyalkolak.retrofit.network;

import com.shadyalkolak.retrofit.models.UserModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiClient {

    @GET(ApiService.END_POINT_USER)
    Call<List<UserModel>> getAllUsers();
}
