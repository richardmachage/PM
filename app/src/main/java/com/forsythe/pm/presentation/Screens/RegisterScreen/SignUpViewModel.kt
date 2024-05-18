package com.forsythe.pm.presentation.Screens.RegisterScreen

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    @ApplicationContext private val context:Context
) : ViewModel() {
    var username = mutableStateOf("")
    var email =  mutableStateOf("")
    var phoneNumber = mutableStateOf("")
    var password = mutableStateOf("")



}