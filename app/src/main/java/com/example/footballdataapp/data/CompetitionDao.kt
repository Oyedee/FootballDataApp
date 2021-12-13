package com.example.footballdataapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.footballdataapp.data.competitions.Competition
import com.example.footballdataapp.data.competitions.Competitions
import com.example.footballdataapp.data.teams.Team
import kotlinx.coroutines.flow.Flow

@Dao
interface CompetitionDao {
    @Query("SELECT * FROM competitions")
    fun getAllCompetitions(): Flow<Competitions>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompetition(competitions: Competitions)

    @Query("DELETE FROM competitions")
    suspend fun deleteAllCompetitions()
}