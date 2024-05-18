package com.forsythe.pm.presentation.Screens.RegisterScreen

import android.content.Context
import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.forsythe.pm.data.sharedPreferences.PreferencesRepo
import com.forsythe.pm.models.RegisterRequest
import com.forsythe.pm.models.RegisterResponse
import com.forsythe.pm.network.ApiService
import com.forsythe.pm.network.RetrofitClient
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    @ApplicationContext private val context:Context,
) : ViewModel() {
    var username = mutableStateOf("")
    var email =  mutableStateOf("")
    var phoneNumber = mutableStateOf("")
    var password = mutableStateOf("")
    var toastMessage = mutableStateOf("")
    var confirmPassword = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    private val apiService = RetrofitClient.getInstance().create(ApiService::class.java)

    fun onSignUp(){
        if (validateInputs()){
            //Try sign up
            try {
                isLoading.value = true
                val registerRequest = RegisterRequest(username.value,email.value,phoneNumber.value,password.value,confirmPassword.value)
                val call = apiService.registerUser(registerRequest)

                call.enqueue(object : Callback<RegisterResponse>{
                    override fun onResponse(
                        call: Call<RegisterResponse>,
                        response: Response<RegisterResponse>
                    ) {
                        if (response.isSuccessful && response.body() != null){
                            isLoading.value = false
                            toastMessage.value = "User created successfully, proceed to login"

                        } else {
                            isLoading.value = false
                            toastMessage.value = "Failed to register: ${response.message()}"
                        }
                    }

                    override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                        Throwable(t)
                    }

                })


            }catch (e:Exception){
                isLoading.value = false
                toastMessage.value = "Failed to register user ${e.message}"
            }
        }

    }

    private fun validateInputs() : Boolean {
        if (username.value.isBlank()) {
            toastMessage.value = "Username cannot be blank"
            return false
        }

        if (email.value.isBlank()) {
            toastMessage.value = "Email cannot be blank"
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email.value).matches()) {
            toastMessage.value = "Invalid email address"
            return false
        }

        if (phoneNumber.value.isBlank()) {
            toastMessage.value = "Phone number cannot be blank"
            return false
        }

        if (password.value.isBlank()) {
            toastMessage.value= "Password cannot be blank"
            return false
        }

        if (confirmPassword.value.isBlank()) {
            toastMessage .value= "Confirm password cannot be blank"
            return false
        }

        if (password.value != confirmPassword.value) {
            toastMessage.value = "Passwords do not match"
            return false
        }

        return true
    }

}