package com.example.footballdataapp.data.teams

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.footballdataapp.util.typeconverters.AreaTypeConverter


@Entity(tableName = "team")
data class Team(
    val address: String,
    val clubColors: String,
    val crestUrl: String,
    val email: String,
    val founded: Int,
    @PrimaryKey val id: Int,
    val lastUpdated: String,
    val name: String,
    val phone: String,
    val shortName: String,
    val tla: String,
    val venue: String,
    val website: String
)