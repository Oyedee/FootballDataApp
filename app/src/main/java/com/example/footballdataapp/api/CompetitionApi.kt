package com.example.footballdataapp.api

import com.example.footballdataapp.data.competitions.Competition
import com.example.footballdataapp.data.competitions.Competitions
import com.example.footballdataapp.data.teamdetails.Squad
import com.example.footballdataapp.data.teamdetails.TeamDetail
import com.example.footballdataapp.data.teams.Team
import com.example.footballdataapp.data.teams.Teams
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CompetitionApi {
    @GET("competitions")
    suspend fun getAllCompetitions(): Competitions

    @GET("competitions/{id}/teams")
    suspend fun getAllTeams(
        @Path("id") competitionId: String
    ): Teams

    @GET("teams/{id}")
    suspend fun getTeamDetails(
        @Path("id") teamId: String
    ): TeamDetail


    companion object {
        const val BASE_URL = "http://api.football-data.org/v2/"
    }
}