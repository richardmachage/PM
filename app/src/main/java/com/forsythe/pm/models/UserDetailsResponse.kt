package com.forsythe.pm.models

data class UserDetailsResponse(
    val code: Int,
    val message: String,
    val data: UserDetails?
)
