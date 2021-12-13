package com.example.footballdataapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.footballdataapp.data.teams.Team
import com.example.footballdataapp.data.teams.Teams
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao {
    @Query("SELECT * FROM teams")
    fun getAllTeams(): Flow<Teams>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeam(teams: Teams)

    @Query("DELETE FROM teams")
    suspend fun deleteAllTeams()
}