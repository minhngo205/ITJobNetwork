package com.example.itnetwork.network;

import com.example.itnetwork.dtos.request.LoginDTORequest;
import com.example.itnetwork.dtos.response.LoginDTOResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ITNetworkApiInterface {
    @POST("api/v1/auth")
    Call<LoginDTOResponse> userLogin(@Body LoginDTORequest params);
}
