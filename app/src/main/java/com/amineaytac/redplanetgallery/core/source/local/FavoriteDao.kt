package com.amineaytac.redplanetgallery.core.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.amineaytac.redplanetgallery.core.data.model.FavoriteModel
@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItemFavorite(photo: FavoriteModel)

    @Query("DELETE FROM favoriteTable WHERE id=:id")
    suspend fun deleteItemFavorite(id: Int)

    @Query("SELECT * FROM favoriteTable")
    fun getItemFavorite(): List<FavoriteModel>

    @Query("SELECT EXISTS(SELECT 1 FROM favoriteTable WHERE id = :itemId LIMIT 1)")
    fun isItemFavorited(itemId: String): LiveData<Boolean>
}