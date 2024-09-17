package com.amineaytac.redplanetgallery.core.network.dto

import com.google.gson.annotations.SerializedName

data class MarsResponse(
    @SerializedName("photos")
    val photos: List<Photo>?
)