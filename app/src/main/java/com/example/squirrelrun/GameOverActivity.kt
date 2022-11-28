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
        binding.homeBtn.setOnClickListener { launchMain() }
        val point = intent.getIntExtra("points", 0)
        val name = intent.getStringExtra("mic")
        if (name != null) {
            binding.points.text = name + " " + resources.getString(R.string.points, point)
        } else {
            binding.points.text =  "Nice " + resources.getString(R.string.points, point)
        }

    }
    private fun launchGame() {
        listIntent = Intent(this, GameActivity::class.java)
        startActivity(listIntent)
    }
    private fun launchMain() {
        listIntent = Intent(this, MainActivity::class.java)
        startActivity(listIntent)
    }
}