package com.example.footballdataapp.features.competitions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.footballdataapp.data.competitions.Competition
import com.example.footballdataapp.databinding.ActivityCompetitionBinding
import com.example.footballdataapp.features.teams.TeamsActivity
import com.example.footballdataapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CompetitionsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCompetitionBinding
    private lateinit var toolbar: Toolbar
    private lateinit var progressBar: ProgressBar
    private lateinit var textViewError: TextView

    private val viewModel by viewModels<CompetitionViewModel>()
    private lateinit var competitionRecyclerView: RecyclerView
    private lateinit var competitionAdapter: CompetitionAdapter
    private lateinit var competitionArrayList: ArrayList<Competition>
    private lateinit var competition: Competition


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompetitionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        toolbar = binding.toolbarCompetitionActivity
        setSupportActionBar(toolbar)

        competitionRecyclerView = binding.competitionRecyclerView
        competitionArrayList = ArrayList()

        progressBar = binding.progressBar
        textViewError = binding.textViewError

        viewModel.competitions.observe(this@CompetitionsActivity) { response ->
            //competitionAdapter.submitList(response.data)
            val result = response.data
            val competitionList: List<Competition>? = result?.competitions
            competitionAdapter.submitList(competitionList)
            competitionList?.forEach {
                competitionArrayList.add(it)
            }

            progressBar.isVisible = response is Resource.Loading && response.data?.competitions.isNullOrEmpty()
            textViewError.isVisible = response is Resource.Error && response.data?.competitions.isNullOrEmpty()
            textViewError.text = response.error?.localizedMessage

        }
        competitionAdapter = CompetitionAdapter(this, competitionArrayList)
        competitionRecyclerView.adapter = competitionAdapter

        selectCompetition()


    }

    private fun selectCompetition() {
        competitionAdapter.setItemClickListener {
            val position = competitionArrayList.indexOf(it)
            competitionArrayList[position] = it
            competition = competitionArrayList[position]
            val competitionId = competition.id

            val intent = Intent(this, TeamsActivity::class.java)
            intent.putExtra("Competition", competitionId)
            startActivity(intent)
        }
    }

}