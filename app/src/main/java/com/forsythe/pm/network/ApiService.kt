package com.forsythe.pm.network

import com.forsythe.pm.models.CreateProjectRequest
import com.forsythe.pm.models.CreateProjectResponse
import com.forsythe.pm.models.LoginCredentials
import com.forsythe.pm.models.LoginResponse
import com.forsythe.pm.models.ProjectDetailsResponse
import com.forsythe.pm.models.ProjectsResponse
import com.forsythe.pm.models.RegisterRequest
import com.forsythe.pm.models.RegisterResponse
import com.forsythe.pm.models.UserDetailsResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("register")
    fun registerUser(@Body request: RegisterRequest): Call<RegisterResponse>

    @POST("login")
    fun logInUser(@Body credentials: LoginCredentials): Call<LoginResponse>

    @GET("projects")
    fun listProjects(@Header("Authorization") token: String): Call<ProjectsResponse>

    @POST("projects")
    fun createProject(@Header("Authorization") token: String, @Body request: CreateProjectRequest):Call<CreateProjectResponse>

    @GET("current_user")
    fun getCurrentUser(@Header("Authorization") token: String): Call<UserDetailsResponse>

    @GET("projects/{id}")
    fun getProjectDetails(
        @Header("Authorization") token: String,
        @Path("id") projectId: String
    ): Call<ProjectDetailsResponse>
}