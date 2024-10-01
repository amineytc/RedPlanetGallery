package com.amineaytac.redplanetgallery.core.data.repo

import androidx.lifecycle.LiveData
import com.amineaytac.redplanetgallery.core.common.Resource
import com.amineaytac.redplanetgallery.core.data.model.FavoriteModel
import com.amineaytac.redplanetgallery.core.source.local.FavoriteDao
import com.amineaytac.redplanetgallery.core.source.remote.MarsRestApi
import javax.inject.Inject

class MarsRepository @Inject constructor(
    private val marsRestApi: MarsRestApi,
    private val favoriteDao: FavoriteDao
) {

    suspend fun getAllMarsPictures() = try {
        Resource.Success(marsRestApi.getAllMarsPicture().body()?.photos)
    } catch (e: Exception) {
        Resource.Error(e.message.orEmpty())
    }

    suspend fun addItemFavorite(item: FavoriteModel) = try {
        Resource.Success(favoriteDao.addItemFavorite(item))
    } catch (e: Exception) {
        Resource.Error(e.message.orEmpty())
    }

    suspend fun deleteItemFavorite(id: Int) {
        favoriteDao.deleteItemFavorite(id)
    }

    fun getItemFavorite(): List<FavoriteModel> {
        return favoriteDao.getItemFavorite()
    }

    fun isItemFavorited(itemId: String): LiveData<Boolean> {
        return favoriteDao.isItemFavorited(itemId)
    }

    suspend fun getSearchMarsPicture(search: String) = try {
        Resource.Success(marsRestApi.getSearchMarsPicture(search).body()?.photos)
    } catch (e: Exception) {
        Resource.Error(e.message.orEmpty())
    }
}