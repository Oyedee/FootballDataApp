package com.example.footballdataapp.data.teams

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.footballdataapp.util.typeconverters.TeamTypeConverter


@Entity(tableName = "teams")
data class Teams(
    @PrimaryKey val count: Int,
    @TypeConverters(TeamTypeConverter::class)
    val teams: List<Team>
)