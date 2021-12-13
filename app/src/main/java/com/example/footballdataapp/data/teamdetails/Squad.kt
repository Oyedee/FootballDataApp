package com.example.footballdataapp.data.teamdetails

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "squad")
data class Squad(
    val countryOfBirth: String,
    val dateOfBirth: String,
    @PrimaryKey val id: Int,
    val name: String,
    val nationality: String,
    val position: String,
    val role: String,
)