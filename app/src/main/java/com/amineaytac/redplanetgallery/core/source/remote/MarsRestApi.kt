package com.amineaytac.redplanetgallery.core.source.remote

import com.amineaytac.redplanetgallery.core.network.dto.MarsResponse
import com.amineaytac.redplanetgallery.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MarsRestApi {

    @GET("rovers/curiosity/photos?sol=1000")
    suspend fun getAllMarsPicture(
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): Response<MarsResponse>

    @GET("rovers/curiosity/photos?sol=1000")
    suspend fun getSearchMarsPicture(
        @Query("camera") camera: String,
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): Response<MarsResponse>
}