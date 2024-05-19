package com.forsythe.pm.presentation.Screens.ProjectDetails

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.forsythe.pm.data.sharedPreferences.ACCESS_TOKEN_KEY
import com.forsythe.pm.data.sharedPreferences.PreferencesRepo
import com.forsythe.pm.models.ProjectDetailsResponse
import com.forsythe.pm.network.api
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class ProjectDetailsViewModel @Inject constructor(
    @ApplicationContext private val context:Context,
   // @Assisted private val projectId : String
) : ViewModel() {
    var isLoading = mutableStateOf(false)
    var toastMessage = mutableStateOf("")
    var tittle = mutableStateOf("")
    var description = mutableStateOf("")
    var createdAt = mutableStateOf("")


    fun fetchProjectDetails(projectId: String){
        isLoading.value = true
        val preferencesRepo = PreferencesRepo(context)
        val token  = preferencesRepo.loadData(ACCESS_TOKEN_KEY)?:""

        if (token.isNotBlank()){
            try {
                val call = api.getProjectDetails("Bearer $token", projectId)
                call.enqueue(object : Callback<ProjectDetailsResponse>{
                    override fun onResponse(call: Call<ProjectDetailsResponse>, response: Response<ProjectDetailsResponse>) {
                        if (response.isSuccessful) {
                            Log.d("get details", response.body().toString())
                            if (response.body() != null && response.body()?.data != null){
                                response.body()?.data?.let {
                                    tittle.value = it.Name
                                    description.value = it.Description
                                    createdAt.value = it.CreatedAt
                                }
                            }
                            else{
                                toastMessage.value = "no data in result"
                            }

                            isLoading.value = false

                        } else {
                            //to.value = "Failed to fetch project details: ${response.message()}"
                            Throwable(message = "Failed to get details ${response.message()}")
                        }
                    }

                    override fun onFailure(call: Call<ProjectDetailsResponse>, t: Throwable) {
                        Throwable(message = "Error : ${t.message}")
                    }
                })
            }
            catch (e :Exception){
                isLoading.value = false
                toastMessage.value = e.message.toString()
                Log.d("get project details", e.message.toString())
            }
        }
    }
}