package com.forsythe.pm.models

data class ApiResponse(
    val code: Int,
    val message: String,
    val data: Any?
)