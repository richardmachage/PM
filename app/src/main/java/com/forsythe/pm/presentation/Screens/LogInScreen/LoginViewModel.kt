package com.forsythe.pm.presentation.Screens.LogInScreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.forsythe.pm.data.sharedPreferences.ACCESS_TOKEN_KEY
import com.forsythe.pm.data.sharedPreferences.PreferencesRepo
import com.forsythe.pm.models.LoginCredentials
import com.forsythe.pm.models.LoginResponse
import com.forsythe.pm.network.ApiService
import com.forsythe.pm.network.RetrofitClient
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
 class LoginViewModel @Inject constructor(
     @ApplicationContext context: ApplicationContext,
     private val preferencesRepo: PreferencesRepo
 ) : ViewModel() {
    var usernameOrEmail = mutableStateOf("")
    var password = mutableStateOf("")
    var toastMessage = mutableStateOf("")

    var isLoading = mutableStateOf(false)
    var performNavigation = mutableStateOf("")

    private val apiService = RetrofitClient.getInstance().create(ApiService::class.java)

    fun onLogIn(){
        if (validateInputs()){
            try {
                isLoading.value = true
                val loginCredentials = LoginCredentials(username_or_email = usernameOrEmail.value, password = password.value )
                val call  = apiService.logInUser(loginCredentials)

                call.enqueue(object : Callback<LoginResponse> {
                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        if (response.isSuccessful && response.body() != null){
                           // Log.d("Log in User", "Success: ${response.body()!!.message}")
                            val responseData = response.body()!!.data
                            preferencesRepo.saveData(ACCESS_TOKEN_KEY, responseData.access_token) //save token securely to preferences
                            isLoading.value = false
                            performNavigation.value = "yes" //navigate to home
                        }

                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Throwable(t)
                    }

                })
            }
            catch (e:Exception){
                isLoading.value = false
                toastMessage.value = "Failed to log in: ${e.localizedMessage}"
            }
        }

    }

    private fun validateInputs():Boolean{
        if (usernameOrEmail.value.isBlank()) {
            toastMessage.value = "Username cannot be blank"
            return false
        }
        if (password.value.isBlank()) {
            toastMessage.value = "Password cannot be blank"
            return false
        }
        return true
    }
}