package com.forsythe.pm.data.sharedPreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

const val ACCESS_TOKEN_KEY = "access_token"
class PreferencesRepo @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val sharedPreferences : SharedPreferences = context.getSharedPreferences(PREFERENCES_FILE_KEY,Context.MODE_PRIVATE)
    companion object{
        private const val PREFERENCES_FILE_KEY = "com.forsythe.PM.PREFERENCES"
    }

    fun saveData(key:String, value:String){
        with(sharedPreferences.edit()){
            putString(key, value)
            apply()
        }
    }

    fun loadData(key:String):String?{
        return sharedPreferences.getString(key,"")
    }

    fun removeData(key:String){
        with(sharedPreferences.edit()){
            remove(key)
            apply()
        }
    }
}