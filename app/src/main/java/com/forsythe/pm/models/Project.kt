package com.forsythe.pm.models

data class Project(
    val Uuid: String,
    val Name: String,
    val Description: String,
    val UserUuid: String,
    val CreatedAt: String,
    val ArchivedAt: ArchivedAt
)


