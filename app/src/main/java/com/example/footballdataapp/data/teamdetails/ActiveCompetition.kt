package com.example.footballdataapp.data.teamdetails

data class ActiveCompetition(
    val area: Area,
    val code: String,
    val id: Int,
    val lastUpdated: String,
    val name: String,
    val plan: String
)