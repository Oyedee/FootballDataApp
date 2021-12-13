package com.example.footballdataapp.data

import androidx.lifecycle.*
import com.example.footballdataapp.data.teamdetails.TeamDetail
import com.example.footballdataapp.data.teams.Teams
import com.example.footballdataapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(
    private val repository: CompetitionRepository
) : ViewModel() {

    fun getTeams(competitionId: String) =
        repository.getTeams(competitionId = "2000").asLiveData()


    fun getTeamDetails(teamId: String) =
        repository.getTeamDetails(teamId).asLiveData()
}