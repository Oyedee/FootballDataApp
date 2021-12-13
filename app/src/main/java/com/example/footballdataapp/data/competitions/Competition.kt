package com.example.footballdataapp.data.competitions

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.footballdataapp.util.typeconverters.AreaTypeConverter
import com.example.footballdataapp.util.typeconverters.CurrentTypeConverter


@Entity(tableName = "competition")
data class Competition(
    @TypeConverters(AreaTypeConverter::class)
    val area: Area,
    val code: String,
    @TypeConverters(CurrentTypeConverter::class)
    val currentSeason: CurrentSeason,
    @PrimaryKey val id: Int,
    val lastUpdated: String,
    val name: String,
    val numberOfAvailableSeasons: Int,
    val plan: String
)