package com.forsythe.pm.network

import com.forsythe.pm.models.ApiResponse
import com.forsythe.pm.models.LoginCredentials
import com.forsythe.pm.models.LoginResponse
import com.forsythe.pm.models.Project
import com.forsythe.pm.models.ProjectsResponse
import com.forsythe.pm.models.RegisterRequest
import com.forsythe.pm.models.RegisterResponse
import com.forsythe.pm.models.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("register")
    fun registerUser(@Body request: RegisterRequest): Call<RegisterResponse>

    @POST("login")
    fun logInUser(@Body credentials: LoginCredentials): Call<LoginResponse>

    @GET("projects")
    fun listProjects(@Header("Authorization") token: String): Call<ProjectsResponse>

}