package com.forsythe.pm.models

data class RegisterRequest(
    val username: String,
    val email: String,
    val phone: String,
    val password: String,
    val confirm_password: String
)
