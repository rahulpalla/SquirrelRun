package com.example.squirrelrun

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.squirrelrun.databinding.GamePageBinding

class GameActivity : AppCompatActivity() {

    private lateinit var binding: GamePageBinding
    private val resultList: ArrayList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = GamePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}