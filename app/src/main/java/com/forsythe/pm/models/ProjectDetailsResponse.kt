package com.forsythe.pm.models

data class ProjectDetailsResponse(
    val code: Int,
    val message: String,
    val data: ProjectDetails?
)