package com.example.footballdataapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.footballdataapp.data.teamdetails.TeamDetail
import kotlinx.coroutines.flow.Flow


@Dao
interface SquadDao {
    @Query("SELECT * FROM teamDetails")
    fun getTeamDetails(): Flow<TeamDetail>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTeamDetails( teamDetail: TeamDetail)

    @Query("DELETE FROM teamDetails")
    suspend fun deleteAllTeamDetails()
}