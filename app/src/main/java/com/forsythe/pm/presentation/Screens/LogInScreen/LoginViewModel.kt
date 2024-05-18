package com.forsythe.pm.presentation.Screens.LogInScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.forsythe.pm.data.sharedPreferences.ACCESS_TOKEN_KEY
import com.forsythe.pm.data.sharedPreferences.PreferencesRepo
import com.forsythe.pm.models.LoginCredentials
import com.forsythe.pm.models.LoginResponse
import com.forsythe.pm.network.ApiService
import com.forsythe.pm.network.RetrofitClient
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val apiService = RetrofitClient.getInstance().create(ApiService::class.java)

    fun logInUser(
        emailOrUsername: String,
        password: String,
        preferencesRepo: PreferencesRepo,
    ){
        val loginCredentials = LoginCredentials(username_or_email = emailOrUsername, password = password )
        val call  = apiService.logInUser(loginCredentials)

        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful && response.body() != null){
                    Log.d("Log in User", "Success: ${response.body()!!.message}")

                    val responseData = response.body()!!.data
                    //accessToken.value = responseData.access_token
                    //store token to shared preferences
                    preferencesRepo.saveData(ACCESS_TOKEN_KEY, responseData.access_token)
                }

            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("Login User", "Error: ${t.message}")
            }

        })
    }
}