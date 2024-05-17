package com.forsythe.pm.models

data class LoginResponse(
    val code: Int,
    val message: String,
    val data: LoginData
)