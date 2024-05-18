package com.forsythe.pm.presentation.Screens.HomeScreen

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.forsythe.pm.data.sharedPreferences.ACCESS_TOKEN_KEY
import com.forsythe.pm.data.sharedPreferences.PreferencesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    @ApplicationContext private val context:Context,

): ViewModel() {
    var token = mutableStateOf("")

    init {
        val preferencesRepo =  PreferencesRepo(context)
        token.value = preferencesRepo.loadData(ACCESS_TOKEN_KEY)?:"No key in preferences"
    }
}