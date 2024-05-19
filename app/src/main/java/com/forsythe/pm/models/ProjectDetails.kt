package com.forsythe.pm.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProjectDetails(
    @Json(name = "Uuid") val uuid: String,
    @Json(name = "Name") val name: String,
    @Json(name = "Description") val description: String,
    @Json(name = "UserUuid") val userUuid: String,
    @Json(name = "CreatedAt") val createdAt: String,
    @Json(name = "ArchivedAt") val archivedAt: ArchivedAt
)