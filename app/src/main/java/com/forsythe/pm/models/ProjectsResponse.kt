package com.forsythe.pm.models

import com.squareup.moshi.JsonClass

//@JsonClass(generateAdapter = true)
data class ProjectsResponse(
    val code: Int,
    val message: String,
    val data: List<Project>?
)
