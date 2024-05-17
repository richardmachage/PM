package com.forsythe.pm.presentation.Screens.MainActivity

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.forsythe.pm.models.LoginCredentials
import com.forsythe.pm.models.LoginResponse
import com.forsythe.pm.models.RegisterRequest
import com.forsythe.pm.models.RegisterResponse
import com.forsythe.pm.network.ApiService
import com.forsythe.pm.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    var name = mutableStateOf("Richard")
    var responseMessage = mutableStateOf("response message")
    var accessToken = mutableStateOf("Access token")
    private val apiService = RetrofitClient.getInstance().create(ApiService::class.java)

    fun registerUser(
        username: String,
        email: String,
        phone: String,
        password: String,
        confirmPassword: String
    ){
        val registerRequest = RegisterRequest(username,email,phone,password,confirmPassword)
        val call = apiService.registerUser(registerRequest)

        call.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                if (response.isSuccessful && response.body() != null){
                    responseMessage.value = response.body()!!.message
                    Log.d("RegisterUser", "Success: ${response.body()!!.message}")

                } else {
                    Log.d("RegisterUser", "Failed: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Log.e("RegisterUser", "Error: ${t.message}")
            }
        })
    }

    fun logInUser(
        emailOrUsername: String ,
        password: String
    ){
        val loginCredentials = LoginCredentials(username_or_email = emailOrUsername, password = password )
        val call  = apiService.logInUser(loginCredentials)

        call.enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful && response.body() != null){
                    val responseData = response.body()!!.data
                    accessToken.value = responseData.access_token
                }
            }

            override fun onFailure(p0: Call<LoginResponse>, p1: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}