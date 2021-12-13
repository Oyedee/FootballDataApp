package com.example.footballdataapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.footballdataapp.data.competitions.Competition
import com.example.footballdataapp.data.competitions.Competitions
import com.example.footballdataapp.data.teamdetails.Squad
import com.example.footballdataapp.data.teamdetails.TeamDetail
import com.example.footballdataapp.data.teams.Team
import com.example.footballdataapp.data.teams.Teams
import com.example.footballdataapp.util.typeconverters.*


@Database(entities = [Competition::class, Team::class, Competitions::class, Teams::class, TeamDetail::class, Squad::class], version = 1)
@TypeConverters(AreaTypeConverter::class, CurrentTypeConverter::class, CompetitionTypeConverter::class, TeamTypeConverter::class, SquadTypeConverter::class)
abstract class CompetitionDatabase: RoomDatabase() {

    abstract fun competitionDao(): CompetitionDao
    abstract fun teamDao(): TeamDao
    abstract fun squadDao(): SquadDao
}