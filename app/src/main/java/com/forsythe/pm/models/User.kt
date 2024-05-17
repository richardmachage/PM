package com.forsythe.pm.models

data class User(
    val username: String,
    val email: String,
    val phone: String,
    val password: String,
    val confirm_password: String
)
