package com.amineaytac.redplanetgallery.core.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "favoriteTable")
data class FavoriteModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int?,

    @ColumnInfo("image")
    val imgSrc: String?,

    @ColumnInfo("name")
    val name: String?,

    @ColumnInfo("fullName")
    val fullName: String?,
)