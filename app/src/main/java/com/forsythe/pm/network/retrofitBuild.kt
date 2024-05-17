package com.forsythe.pm.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

private const val BASE_URL = "https://mngmtapp.malakoff.co/api/v1/"

private val client = OkHttpClient.Builder()
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create())
    .client(client)
    .baseUrl(BASE_URL)
    .build()

val api: ApiService = retrofit.create(ApiService::class.java)



