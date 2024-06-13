package com.aplikasi.gizilo.data.api

import com.aplikasi.gizilo.data.response.LoginRequest
import com.aplikasi.gizilo.data.response.LoginResponse
import com.aplikasi.gizilo.data.response.RegisterRequest
import com.aplikasi.gizilo.data.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @POST("auth/register")
    suspend fun doRegister(
        @Body registerRequest : RegisterRequest
    ): RegisterResponse

    @POST("auth/login")
    suspend fun doLogin(
      @Body loginRequest: LoginRequest
    ): LoginResponse
}