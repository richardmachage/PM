package com.forsythe.pm.presentation.Screens.NewProjectScreen

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.forsythe.pm.data.sharedPreferences.ACCESS_TOKEN_KEY
import com.forsythe.pm.data.sharedPreferences.PreferencesRepo
import com.forsythe.pm.models.CreateProjectRequest
import com.forsythe.pm.models.CreateProjectResponse
import com.forsythe.pm.network.ApiService
import com.forsythe.pm.network.RetrofitClient
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class NewProjectViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {
    var projectName = mutableStateOf("")
    var projectDescription = mutableStateOf("")
    var toastMessage = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    val preferencesRepo = PreferencesRepo(context)

    fun createProject(){
        isLoading.value = true
        val token = preferencesRepo.loadData(ACCESS_TOKEN_KEY)?:""
        if (token.isNotBlank()){
            try {
                val api = RetrofitClient.getAuthenticatedInstance(token).create(ApiService::class.java)
                val createProjectRequest = CreateProjectRequest(name = projectName.value, description = projectDescription.value)
                val call  = api.createProject("Bearer $token",createProjectRequest)

                call.enqueue(object : Callback<CreateProjectResponse>{
                    override fun onResponse(
                        call: Call<CreateProjectResponse>,
                        response: Response<CreateProjectResponse>
                    ) {
                        if (response.isSuccessful){
                            val createProjectResponse = response.body()
                            clearFields()
                            if (createProjectResponse != null && createProjectResponse.data != null){
                               // val projectRes = createProjectResponse.data
                                toastMessage.value = response.message()
                                isLoading.value = false
                            }
                            else{


                                toastMessage.value = response.message()
                                isLoading.value = false
                                Throwable(message = response.message())
                            }
                        }
                        else{
                            Log.d("Create Project:", "Response not successful: ${response.message()}")
                            toastMessage.value = response.message()
                            isLoading.value = false
                            Throwable(message = response.message())
                        }
                    }

                    override fun onFailure(call: Call<CreateProjectResponse>, t: Throwable) {
                        Log.e("Create Project:", "Error: ${t.message}")
                        toastMessage.value = t.message.toString()
                        isLoading.value = false
                        Throwable(message = t.message )
                    }

                })
            }catch (e:Exception){
                toastMessage.value = "${e.message}"
            }
        }
    }
    private fun validateInputs():Boolean{
        if (projectName.value.isBlank()){
            toastMessage.value = "Project name is empty"
            return false
        }

        if (projectDescription.value.isBlank()){
            toastMessage.value = "Project description is empty"
            return false
        }
        return  true
    }

    private fun clearFields(){
        projectName.value = ""
        projectDescription.value = ""
    }
}