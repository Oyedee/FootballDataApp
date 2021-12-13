package com.example.footballdataapp.features.competitions

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.footballdataapp.data.competitions.Competition
import com.example.footballdataapp.databinding.CompetitionListitemBinding

class CompetitionAdapter(val context: Activity, val competitionList: List<Competition>):
   ListAdapter<Competition,
            CompetitionAdapter.CompetitionViewHolder>(CompetitionComparator()) {

    private var onItemClickListener: ((competition: Competition) -> Unit)? = null

    fun setItemClickListener(listener: (competition: Competition) -> Unit) {
        onItemClickListener = listener
    }

    inner class CompetitionViewHolder(private val binding: CompetitionListitemBinding) :
                RecyclerView.ViewHolder(binding.root) {

                    fun bind(competition: Competition) {
                        binding.apply {
                            textLeagueName.text = competition.name
                            textCountry.text = competition.area.name
                            textStartDate.text = competition.currentSeason.startDate
                            cardCompetition.setOnClickListener {
                                onItemClickListener?.invoke(competition)
                            }
                        }
                    }
                }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompetitionViewHolder {
        val binding =
            CompetitionListitemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CompetitionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CompetitionViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class CompetitionComparator : DiffUtil.ItemCallback<Competition>() {
        override fun areItemsTheSame(oldItem: Competition, newItem: Competition): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Competition, newItem: Competition): Boolean =
            oldItem ==  newItem
    }
}