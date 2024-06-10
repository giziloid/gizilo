package com.aplikasi.gizilo.data.api

import com.aplikasi.gizilo.data.response.LoginResponse
import com.aplikasi.gizilo.data.response.RegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("auth/register")
    suspend fun doRegister(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): RegisterResponse

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun doLogin(
        @Field("email") email: String,
        @Field("password") password: String
    ): LoginResponse
}