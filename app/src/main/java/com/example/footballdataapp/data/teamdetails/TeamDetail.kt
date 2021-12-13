package com.example.footballdataapp.data.teamdetails

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.footballdataapp.util.typeconverters.SquadTypeConverter

@Entity(tableName = "teamDetails")
data class TeamDetail(
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
    @TypeConverters(SquadTypeConverter::class)
    val squad: List<Squad>,
    val tla: String,
    val venue: String,
    val website: String
)