package com.example.footballdataapp.data.competitions

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.footballdataapp.util.typeconverters.CompetitionTypeConverter

@Entity(tableName = "competitions")
data class Competitions(
    @TypeConverters(CompetitionTypeConverter::class)
    val competitions: List<Competition>,
    @PrimaryKey val count: Int
)