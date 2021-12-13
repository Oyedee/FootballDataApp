package com.example.footballdataapp.features.teams

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.bumptech.glide.Glide
import com.example.footballdataapp.R
import com.example.footballdataapp.data.competitions.Competition
import com.example.footballdataapp.data.teams.Team
import com.example.footballdataapp.databinding.CompetitionListitemBinding
import com.example.footballdataapp.databinding.TeamsListitemBinding
import com.example.footballdataapp.features.competitions.CompetitionAdapter

class TeamsAdapter(val context: Activity, val competitionList: List<Team>) : ListAdapter<Team,
        TeamsAdapter.TeamsViewHolder>(TeamComparator()) {

    private var onItemClickListener: ((team: Team) -> Unit)? = null

    fun setItemClickListener(listener: (team: Team) -> Unit) {
        onItemClickListener = listener
    }

    inner class TeamsViewHolder(private val binding: TeamsListitemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(team: Team) {
            binding.apply {
                teamIcon.loadUrl(team.crestUrl)
                cardTeam.setOnClickListener {
                    onItemClickListener?.invoke(team)
                }
            }
        }

        fun ImageView.loadUrl(url: String?) {

            val imageLoader = ImageLoader.Builder(this.context)
                .componentRegistry { add(SvgDecoder(this@loadUrl.context)) }
                .build()

            val request = ImageRequest.Builder(this.context)
                .crossfade(true)
                .crossfade(500)
                .placeholder(R.drawable.teamicon)
                .error(R.drawable.teamicon)
                .data(url)
                .target(this)
                .build()

            imageLoader.enqueue(request)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TeamsAdapter.TeamsViewHolder {
        val binding =
            TeamsListitemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamsAdapter.TeamsViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class TeamComparator : DiffUtil.ItemCallback<Team>() {
        override fun areItemsTheSame(oldItem: Team, newItem: Team): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(oldItem: Team, newItem: Team): Boolean =
            oldItem == newItem
    }
}