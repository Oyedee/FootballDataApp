package com.example.footballdataapp.features.teams

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.footballdataapp.R
import com.example.footballdataapp.data.TeamViewModel
import com.example.footballdataapp.data.teams.Team
import com.example.footballdataapp.data.teams.Teams
import com.example.footballdataapp.databinding.ActivityCompetitionBinding
import com.example.footballdataapp.databinding.ActivityTeamsBinding
import com.example.footballdataapp.features.competitions.CompetitionViewModel
import com.example.footballdataapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTeamsBinding
    private lateinit var toolbar: Toolbar
    private lateinit var progressBar: ProgressBar
    private lateinit var textViewError: TextView

    private val viewModel by viewModels<TeamViewModel>()
    private lateinit var teamRecyclerView: RecyclerView
    private lateinit var teamAdapter: TeamsAdapter
    private lateinit var teamArrayList: ArrayList<Team>
    private lateinit var team: Team

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        toolbar = binding.toolbarTeamsActivity
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        //get selected competition id from the list of competitions
        val competitionId: String = intent.getIntExtra("Competition", -1).toString()

        teamRecyclerView = binding.teamsRecyclerView
        teamArrayList = ArrayList()
        progressBar = binding.progressBar
        textViewError = binding.textViewError

        viewModel.getTeams(competitionId).observe(this) { response ->
            val result = response.data
            val teamList: List<Team>? = result?.teams
            teamAdapter.submitList(teamList)
            teamList?.forEach {
                teamArrayList.add(it)
            }

            progressBar.isVisible = response is Resource.Loading<*> && response.data?.teams.isNullOrEmpty()
            textViewError.isVisible = response is Resource.Error<*> && response.data?.teams.isNullOrEmpty()
            textViewError.text = response.error?.localizedMessage

        }
        teamAdapter = TeamsAdapter(this, teamArrayList)
        teamRecyclerView.adapter = teamAdapter
        selectTeam()
    }

    private fun selectTeam() {
        teamAdapter.setItemClickListener {
            val position = teamArrayList.indexOf(it)
            teamArrayList[position] = it
            team = teamArrayList[position]
            val teamId = team.id

            val intent = Intent(this, TeamDetailsActivity::class.java)
            intent.putExtra("teamId", teamId)
            startActivity(intent)
        }
    }
}