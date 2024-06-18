package com.aplikasi.gizilo.data.pref

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.aplikasi.gizilo.data.api.ApiService
import com.aplikasi.gizilo.data.repository.Result
import com.aplikasi.gizilo.data.response.GetProductResponseItem
import com.aplikasi.gizilo.data.response.LoginRequest
import com.aplikasi.gizilo.data.response.LoginResponse
import com.aplikasi.gizilo.data.response.PostProductResponse
import com.aplikasi.gizilo.data.response.RegisterRequest
import com.aplikasi.gizilo.data.response.RegisterResponse
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException
import java.io.File

class UserRepository private constructor(
    private val apiService: ApiService,
    private val userPreference: UserPreference
) {
    suspend fun saveAuthToken(token: String) {
        userPreference.saveAuthToken(token)
    }

    fun getAuthToken(): Flow<String?> {
        return userPreference.getAuthToken()
    }

    suspend fun saveLogin(loginSession: Boolean) {
        userPreference.saveLoginSession(loginSession)
    }

    fun getLogin(): Flow<Boolean> {
        return userPreference.getLoginSession()
    }

    suspend fun logout() {
        userPreference.clearData()
    }

    fun register(
        username: String,
        email: String,
        password: String
    ): LiveData<Result<RegisterResponse>> = liveData {
        emit(Result.Loading)
        try {
            val registerRequest = RegisterRequest(username, email, password)
            val response = apiService.doRegister(registerRequest)
            emit(Result.Success(response))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody.message
            emit(Result.Error(errorMessage.toString()))
        }
    }

    fun login(email: String, password: String): LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)
        try {
            val loginResponse = LoginRequest(email, password)
            val response = apiService.doLogin(loginResponse)
            emit(Result.Success(response))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody.message
            emit(Result.Error(errorMessage.toString()))
        }
    }

    fun addProduct(
        imageFile: File,
        name: String,
        calories: String,
        fat: String,
        proteins: String,
        carbohydrate: String,
        sugar: String,
        sodium: String,
        weight: String
    ): LiveData<Result<PostProductResponse>> = liveData {
           emit(Result.Loading)
        val reqImage = imageFile.asRequestBody("image/jpeg".toMediaType())
        val multipartImage = MultipartBody.Part.createFormData("images", imageFile.name, reqImage)
        val reqName = name.toRequestBody("text/plain".toMediaType())
        val reqCalories = calories.toRequestBody("text/plain".toMediaType())
        val reqFat = fat.toRequestBody("text/plain".toMediaType())
        val reqProtein = proteins.toRequestBody("text/plain".toMediaType())
        val reqCarbohydrate = carbohydrate.toRequestBody("text/plain".toMediaType())
        val reqSugar = sugar.toRequestBody("text/plain".toMediaType())
        val reqSodium = sodium.toRequestBody("text/plain".toMediaType())
        val reqWeight = weight.toRequestBody("text/plain".toMediaType())

        try {
            val response = apiService.addProduct(
                multipartImage,
                reqName,
                reqCalories,
                reqFat,
                reqProtein,
                reqCarbohydrate,
                reqSugar,
                reqSodium,
                reqWeight
            )
            emit(Result.Success(response))
        } catch (e: HttpException) {
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody.message
            emit(Result.Error(errorMessage.toString()))
        }
    }

    fun getProducts(): LiveData<Result<List<GetProductResponseItem>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getProducts()
            emit(Result.Success(response))
        }catch (e:HttpException){
            val jsonInString = e.response()?.errorBody()?.string()
            val errorBody = Gson().fromJson(jsonInString, ErrorResponse::class.java)
            val errorMessage = errorBody.message
            emit(Result.Error(errorMessage.toString()))
        }
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun clearDataFactory() {
            instance = null
        }

        fun getInstance(
            apiService: ApiService,
            userPreference: UserPreference
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService, userPreference)
            }.also { instance = it }
    }
}