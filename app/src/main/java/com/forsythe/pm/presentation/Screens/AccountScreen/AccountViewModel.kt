package com.forsythe.pm.presentation.Screens.AccountScreen

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.forsythe.pm.data.sharedPreferences.ACCESS_TOKEN_KEY
import com.forsythe.pm.data.sharedPreferences.PreferencesRepo
import com.forsythe.pm.models.UserDetails
import com.forsythe.pm.models.UserDetailsResponse
import com.forsythe.pm.network.ApiService
import com.forsythe.pm.network.RetrofitClient
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    @ApplicationContext context:Context
): ViewModel() {
    var preferencesRepo:PreferencesRepo
    var username = mutableStateOf("")
    var email = mutableStateOf("")
    var phone = mutableStateOf("")
    var navigateAway = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    //var userDetails : UserDetails

    init {
        preferencesRepo = PreferencesRepo(context)
        fetchUserDetails()
    }
    private fun fetchUserDetails(){

        preferencesRepo.loadData(ACCESS_TOKEN_KEY)?.let {token->
            val apiService = RetrofitClient.getAuthenticatedInstance(token).create(ApiService::class.java)
            val call = apiService.getCurrentUser("Bearer $token")

            call.enqueue(object : Callback<UserDetailsResponse>{
                override fun onResponse(
                    call: Call<UserDetailsResponse>,
                    response: Response<UserDetailsResponse>
                ) {
                    if (response.isSuccessful) {
                        username.value = response.body()?.data?.Username ?: ""
                        email.value = response.body()?.data?.Email ?: ""
                        phone.value = response.body()?.data?.Phone ?: ""
                    } else {
                        val errorMessage = "Failed to fetch user details: ${response.message()}"
                        Log.d("Fetch user details", errorMessage)
                    }
                }

                override fun onFailure(call: Call<UserDetailsResponse>, t: Throwable) {
                    Log.d("Fetch user details", t.message.toString())
                }

            })

        }

    }

    fun onLogOut(){
        preferencesRepo.removeData(ACCESS_TOKEN_KEY)
        navigateAway.value = "yes" // use launch effect on account screen
    }
}