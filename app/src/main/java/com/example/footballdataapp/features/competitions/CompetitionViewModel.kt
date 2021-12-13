package com.example.footballdataapp.features.competitions

import androidx.lifecycle.*
import com.example.footballdataapp.data.CompetitionRepository
import com.example.footballdataapp.data.teams.Teams
import com.example.footballdataapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class CompetitionViewModel @Inject constructor(
    private val repository: CompetitionRepository
) : ViewModel(), LifecycleObserver {

    val competitions = repository.getCompetitions().asLiveData()


}