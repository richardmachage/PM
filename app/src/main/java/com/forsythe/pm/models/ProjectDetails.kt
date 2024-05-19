package com.forsythe.pm.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProjectDetails(
     val Uuid: String,
     val Name: String,
     val Description: String,
     val UserUuid: String,
     val CreatedAt: String,
     val ArchivedAt: ArchivedAt
)