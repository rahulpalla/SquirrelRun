package com.example.squirrelrun

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.Toast
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat.startActivity
import com.example.squirrelrun.databinding.ActivityMainBinding

private const val SPEECH_REQUEST_CODE = 0

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var listIntent: Intent
    private var spokenText: String = "Nice"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.playBtn.setOnClickListener { launchStart() }
        binding.settingBtn.setOnClickListener { launchSettings() }
        binding.voiceBtn.setOnClickListener { displaySpeechRecognizer() }

    }
    private fun launchStart() {
        listIntent = Intent(this, StartActivity::class.java)
        listIntent.putExtra("mic", spokenText)
        startActivity(listIntent)
    }
    private fun launchSettings() {
        listIntent = Intent(this, SettingsActivity::class.java)
        listIntent.putExtra("mic", spokenText)
        startActivity(listIntent)
    }

    // Create an intent that can start the Speech Recognizer activity
    private fun displaySpeechRecognizer() {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
            putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        }
        // This starts the activity and populates the intent with the speech text.
        startActivityForResult(intent, SPEECH_REQUEST_CODE)
    }

    // This callback is invoked when the Speech Recognizer returns.
// This is where you process the intent and extract the speech text from the intent.
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            spokenText =
                data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).let { results ->
                    results!![0]
                }

            // Do something with spokenText.
            Toast.makeText(applicationContext, spokenText, Toast.LENGTH_SHORT).show()

        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}