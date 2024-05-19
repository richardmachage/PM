package com.forsythe.pm.presentation.Screens.MainActivity

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.forsythe.pm.data.sharedPreferences.ACCESS_TOKEN_KEY
import com.forsythe.pm.data.sharedPreferences.PreferencesRepo
import com.forsythe.pm.models.LoginCredentials
import com.forsythe.pm.models.LoginResponse
import com.forsythe.pm.models.Project
import com.forsythe.pm.models.ProjectsResponse
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
    var accessToken = mutableStateOf("No token")
    private val apiService = RetrofitClient.getInstance().create(ApiService::class.java)


}