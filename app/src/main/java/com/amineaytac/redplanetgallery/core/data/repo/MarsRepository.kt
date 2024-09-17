package com.amineaytac.redplanetgallery.core.data.repo

import com.amineaytac.redplanetgallery.core.common.Resource
import com.amineaytac.redplanetgallery.core.network.rest.MarsRestApi
import javax.inject.Inject

class MarsRepository @Inject constructor(
    private val marsRestApi :MarsRestApi
){

    suspend fun getAllMarsPictures() = try{
        Resource.Success(marsRestApi.getAllMarsPicture().body()?.photos)
    }catch(e:Exception){
        Resource.Error(e.message.orEmpty())
    }
}