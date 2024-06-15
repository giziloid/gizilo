package com.aplikasi.gizilo.data.api

import com.aplikasi.gizilo.data.response.GetProductResponse
import com.aplikasi.gizilo.data.response.LoginRequest
import com.aplikasi.gizilo.data.response.LoginResponse
import com.aplikasi.gizilo.data.response.PostProductResponse
import com.aplikasi.gizilo.data.response.RegisterRequest
import com.aplikasi.gizilo.data.response.RegisterResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @POST("auth/register")
    suspend fun doRegister(
        @Body registerRequest : RegisterRequest
    ): RegisterResponse

    @POST("auth/login")
    suspend fun doLogin(
      @Body loginRequest: LoginRequest
    ): LoginResponse

    @Multipart
    @POST("products")
    suspend fun addProduct(
        @Part file: MultipartBody.Part,
        @Part("name") name: RequestBody,
        @Part("calories") calories: RequestBody,
        @Part("total_fat") totalFat: RequestBody,
        @Part("protein") protein: RequestBody,
        @Part("carbohydrates") carbohydrates: RequestBody,
        @Part("sugar") sugar: RequestBody,
        @Part("sodium") sodium: RequestBody
    ): PostProductResponse

    @GET("products")
    suspend fun getProducts(): GetProductResponse
}