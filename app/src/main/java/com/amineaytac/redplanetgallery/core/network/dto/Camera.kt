package com.amineaytac.redplanetgallery.core.network.dto

import com.google.gson.annotations.SerializedName

data class Camera(
    @SerializedName("full_name")
    val fullName: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("rover_id")
    val roverİd: Int?
)