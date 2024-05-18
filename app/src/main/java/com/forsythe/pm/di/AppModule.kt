package com.forsythe.pm.di

import android.content.Context
import androidx.compose.ui.tooling.preview.Preview
import com.forsythe.pm.data.sharedPreferences.PreferencesRepo
import com.forsythe.pm.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    @Provides
    @Singleton
    fun providesContext(@ApplicationContext context: Context) : Context{
        return context
    }

    @Provides
    @Singleton
    fun providesPreferencesRepo(context: Context): PreferencesRepo{
        return providesPreferencesRepo(context)
    }

    /*@Provides
    @Singleton
    fun providesApiService():ApiService{
        val service  = ApiService
        return ApiService
    }*/
}