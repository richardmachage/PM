package com.forsythe.pm.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProjectDetailsResponse(
    val code: Int,
    val message: String,
    val data: ProjectDetails?
)