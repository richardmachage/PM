package com.forsythe.pm.models

data class LoginCredentials(
    val username_or_email: String,
    val password: String
)