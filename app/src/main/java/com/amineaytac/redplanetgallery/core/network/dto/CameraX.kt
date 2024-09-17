package com.amineaytac.redplanetgallery.core.network.dto

import com.google.gson.annotations.SerializedName

data class CameraX(
    @SerializedName("full_name")
    val fullName: String?,
    @SerializedName("name")
    val name: String?
)