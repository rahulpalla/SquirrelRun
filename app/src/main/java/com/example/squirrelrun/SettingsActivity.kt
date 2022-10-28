package com.example.squirrelrun

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.squirrelrun.databinding.SettingsBinding

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: SettingsBinding
    private val resultList: ArrayList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}