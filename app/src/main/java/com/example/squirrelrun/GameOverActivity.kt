package com.example.squirrelrun

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.squirrelrun.databinding.GameOverBinding
import com.example.squirrelrun.databinding.SettingsBinding

class GameOverActivity : AppCompatActivity() {

    private lateinit var binding: GameOverBinding
    private lateinit var listIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = GameOverBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.playGameBtn.setOnClickListener { launchGame() }
        val point = listIntent?.getIntExtra("points", 0)
        binding.points.text = resources.getString(point, "Points:")

    }
    private fun launchGame() {
        listIntent = Intent(this, GameActivity::class.java)
        startActivity(listIntent)
    }
}