package com.forsythe.pm.presentation.Screens.HomeScreen

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.forsythe.pm.data.sharedPreferences.ACCESS_TOKEN_KEY
import com.forsythe.pm.data.sharedPreferences.PreferencesRepo
import com.forsythe.pm.models.Project
import com.forsythe.pm.models.ProjectsResponse
import com.forsythe.pm.network.ApiService
import com.forsythe.pm.network.RetrofitClient
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext private val context: Context,

    ) : ViewModel() {
    var token = mutableStateOf("")
    var listOfProjects = mutableStateListOf<Project>()// MutableList<Project> = mutableListOf()
    var isLoading = mutableStateOf(false)
    var toastMessage = mutableStateOf("")
    var refreshList = mutableStateOf(0)

    init {
        val preferencesRepo = PreferencesRepo(context)
        token.value = preferencesRepo.loadData(ACCESS_TOKEN_KEY) ?: "No key in preferences"
        refreshList.value + 1

    }

    fun fetchProjects() {
        try {
            isLoading.value = true
            val api = RetrofitClient.getAuthenticatedInstance(token = token.value)
                .create(ApiService::class.java)
            val call = api.listProjects("Bearer ${token.value}")

            call.enqueue(object : Callback<ProjectsResponse> {
                override fun onResponse(
                    call: Call<ProjectsResponse>,
                    response: Response<ProjectsResponse>
                ) {
                    if (response.isSuccessful) {
                        val projectsResponse = response.body()
                        val projects = projectsResponse?.data
                        if (projects != null) {
                            listOfProjects.clear()
                            listOfProjects.addAll(projects)
                            isLoading.value = false
                            //Log.d("Fetch Projects:", "you have ${projects.size} projects")
                        } else {
                            isLoading.value = false
                            toastMessage.value = "You have no projects"
                            //Log.d("Fetch Projects:", "No projects found for this user")
                        }
                    } else {
                        Throwable(message = response.message())
                        //Log.d("Error", token.value +" is "+ response.message())
                    }
                }

                override fun onFailure(call: Call<ProjectsResponse>, t: Throwable) {
                    Throwable(t)
                }

            })
        } catch (e: Exception) {
            isLoading.value = false
            toastMessage.value = "${e.message}"
        }


    }
}