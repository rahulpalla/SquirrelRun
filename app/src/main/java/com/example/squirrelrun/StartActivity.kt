package com.example.squirrelrun

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.squirrelrun.databinding.ActivityMainBinding
import com.example.squirrelrun.databinding.StartPageBinding

class StartActivity : AppCompatActivity() {

    private lateinit var binding: StartPageBinding
    private lateinit var listIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = StartPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.startBtn.setOnClickListener { launchGame() }
    }
    private fun launchGame() {
        listIntent = Intent(this, GameActivity::class.java)
        startActivity(listIntent)
    }
}