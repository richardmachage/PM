package com.forsythe.pm.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArchivedAt(
    val Time: String,
    val Valid: Boolean
)

