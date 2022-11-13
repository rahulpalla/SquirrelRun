package com.example.squirrelrun

import android.content.Intent

import android.os.Bundle

import android.widget.ImageView

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import com.example.squirrelrun.databinding.GamePageBinding



private const val DEBUG_TAG = "Gestures"

class GameActivity : AppCompatActivity() {

    private lateinit var binding: GamePageBinding
    private lateinit var listIntent: Intent
    private lateinit var mDetector: GestureDetectorCompat

    //    private lateinit var gameView: GameView
    private lateinit var squirrel: ImageView
    private lateinit var wolf: ImageView
    private lateinit var acorn: ImageView
    private var isPlaying: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = GamePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.settingsBtn.setOnClickListener { launchSettings() }
        binding.homeBtn.setOnClickListener { launchHome() }

        setContentView(GameView(this))
    }

    private fun launchSettings() {
        listIntent = Intent(this, SettingsActivity::class.java)
        startActivity(listIntent)
    }
    private fun launchHome() {
        listIntent = Intent(this, MainActivity::class.java)
        startActivity(listIntent)
    }
}

