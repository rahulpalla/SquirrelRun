package com.example.squirrelrun

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.squirrelrun.databinding.SettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: SettingsBinding
    private lateinit var listIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = SettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.gameBtn.setOnClickListener { launchStart() }
        binding.homeBtn.setOnClickListener { launchHome() }
        val webIntent: Intent = Uri.parse("https://docs.google.com/forms/d/e/1FAIpQLScvEchFvcV3nsaqBkX5kmHwAz1ZNGmEJlh5lOoZZ8eqr4F3PA/viewform?usp=sf_link").let { webpage ->
            Intent(Intent.ACTION_VIEW, webpage)
        }
        binding.feedbackButton.setOnClickListener {
            launchLauncher(webIntent)
        }}

    private fun launchLauncher(webIntent: Intent) {
        startActivity(webIntent)
    }
    private fun launchStart() {
        listIntent = Intent(this, StartActivity::class.java)
        startActivity(listIntent)
    }
    private fun launchHome() {
        listIntent = Intent(this, MainActivity::class.java)
        startActivity(listIntent)
    }
}