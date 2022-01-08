package com.example.moviebox.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.moviebox.R
import com.example.moviebox.databinding.ActivityTrailerBinding
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer

class TrailerActivity : YouTubeBaseActivity() {

    private lateinit var binding: ActivityTrailerBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrailerBinding.inflate(layoutInflater)
        setContentView(binding.root)



        getVideoUrl(intent.getStringExtra("ID")!!)

    }

    private fun getVideoUrl(id: String) {

        val listener = object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                p1?.loadVideo(id)
                p1?.play()
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {

                Log.d("TrailerActivity", p1.toString())
                Toast.makeText(this@TrailerActivity, "Something went wrong", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.youtubePlayer.initialize("AIzaSyAxwh3js1NewG-Usi_ST65z8hm193pB_Aw", listener)

    }
}