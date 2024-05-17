package com.forsythe.pm.models

data class RegisterResponse(
    val code: Int,
    val message: String,
    val data: Any?
)
