package com.forsythe.pm.presentation.Screens.MainActivity

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
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

    fun logInUser(){

    }
}