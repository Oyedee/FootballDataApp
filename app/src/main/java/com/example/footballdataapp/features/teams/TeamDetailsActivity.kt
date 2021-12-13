package com.example.footballdataapp.features.teams

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.footballdataapp.R
import com.example.footballdataapp.data.TeamViewModel
import com.example.footballdataapp.data.teamdetails.Squad
import com.example.footballdataapp.databinding.ActivityTeamDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TeamDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTeamDetailsBinding
    private lateinit var toolbar: Toolbar

    private val viewModel by viewModels<TeamViewModel>()
    private lateinit var teamDetailArrayList: ArrayList<Squad>
    private lateinit var founded: TextView
    private lateinit var nickName: TextView
    private lateinit var phone: TextView
    private lateinit var website: TextView
    private lateinit var address: TextView
    private lateinit var imageLogo: ImageView
    private lateinit var email: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        toolbar = binding.toolbarTeamDetailsActivity
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        val teamId: String = intent.getIntExtra("teamId", 0).toString()

        teamDetailArrayList = ArrayList()

        address = binding.textAddress
        nickName = binding.textNickname
        phone = binding.textPhone
        website = binding.textWebsite
        imageLogo = binding.imageView
        email = binding.textEmail
        founded = binding.textView3

        viewModel.getTeamDetails(teamId).observe(this) { response ->
            val result = response.data
            address.text = result?.address
            nickName.text = result?.shortName
            phone.text = result?.phone
            website.text = result?.website
            email.text = result?.email
            founded.text = result?.venue
            toolbar.title = result?.name

            imageLogo.loadUrl(result?.crestUrl)



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