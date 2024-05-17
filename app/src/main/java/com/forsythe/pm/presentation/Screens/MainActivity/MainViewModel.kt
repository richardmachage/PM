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
        password: String,
        preferencesRepo: PreferencesRepo,
    ){
        val loginCredentials = LoginCredentials(username_or_email = emailOrUsername, password = password )
        val call  = apiService.logInUser(loginCredentials)

        call.enqueue(object : Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful && response.body() != null){
                    Log.d("Log in User", "Success: ${response.body()!!.message}")

                    val responseData = response.body()!!.data
                    accessToken.value = responseData.access_token
                    //store token to shared preferences
                    preferencesRepo.saveData(ACCESS_TOKEN_KEY, responseData.access_token)
                }

            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("Login User", "Error: ${t.message}")
            }

        })
    }

    fun fetchProjects(token : String){
        val api  = RetrofitClient.getAuthenticatedInstance(token = token).create(ApiService::class.java)
        val call  = api.listProjects("Bearer $token")

        call.enqueue(object : Callback<ProjectsResponse>{
            override fun onResponse(call: Call<ProjectsResponse>, response: Response<ProjectsResponse>) {
                if (response.isSuccessful){
                    val projectsResponse  = response.body()
                    val projects = projectsResponse?.data
                    if (projects != null) {
                        Log.d("Fetch Projects:", "you have ${projects.size} projects")
                    }
                    else{
                        Log.d("Fetch Projects:", "No projects found for this user")
                    }
                }
                else{
                    Log.d("Error", accessToken.value +" is "+ response.message())
                }
            }

            override fun onFailure(call: Call<ProjectsResponse>, t: Throwable) {
                Log.e("Fetch Projects:", " ${t.message}")
            }

        })

    }
}