package com.amineaytac.redplanetgallery.core.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.amineaytac.redplanetgallery.core.data.model.FavoriteModel
@Database(entities = [FavoriteModel::class], version = 2)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun getItemDao(): FavoriteDao
}