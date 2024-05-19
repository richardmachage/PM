package com.forsythe.pm.models

data class CreateProjectResponse(
    val code: Int,
    val message: String,
    val data: Project?
)