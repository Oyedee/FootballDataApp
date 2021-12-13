package com.example.footballdataapp.data

import android.content.SharedPreferences
import androidx.room.withTransaction
import com.example.footballdataapp.api.CompetitionApi
import com.example.footballdataapp.util.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class CompetitionRepository @Inject constructor(
    private val api: CompetitionApi,
    private val db: CompetitionDatabase,
) {
    private val competitionDao = db.competitionDao()
    private val teamDao = db.teamDao()
    private val squadDao = db.squadDao()

    fun getCompetitions() = networkBoundResource(
        query = {
            competitionDao.getAllCompetitions()
        },
        fetch = {
            delay(2000)
            api.getAllCompetitions()
        },
        saveFetchResult = { competitions ->
            db.withTransaction {
                competitionDao.deleteAllCompetitions()
                competitionDao.insertCompetition(competitions)
            }
        }
    )

    fun getTeams(competitionId: String) = networkBoundResource(
        query = {
            teamDao.getAllTeams()
        },
        fetch = {
            delay(2000)
            api.getAllTeams(competitionId)
        },
        saveFetchResult = { teams ->
            db.withTransaction {
                teamDao.deleteAllTeams()
                teamDao.insertTeam(teams)
            }
        }
    )

    fun getTeamDetails(teamId: String) = networkBoundResource(
        query = {
            squadDao.getTeamDetails()
        },
        fetch = {
            delay(2000)
            api.getTeamDetails(teamId)
        },
        saveFetchResult = { squad ->
            db.withTransaction {
                squadDao.deleteAllTeamDetails()
                squadDao.insertTeamDetails(squad)
            }
        }
    )


}