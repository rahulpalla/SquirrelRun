package com.example.squirrelrun

import android.app.Notification
import android.content.Intent
import android.graphics.Point
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GestureDetectorCompat
import com.example.squirrelrun.databinding.GamePageBinding

private const val DEBUG_TAG = "Gestures"

class GameActivity : AppCompatActivity(), GestureDetector.OnGestureListener,
    GestureDetector.OnDoubleTapListener {

    private lateinit var binding: GamePageBinding
    private lateinit var listIntent: Intent
    private lateinit var mDetector: GestureDetectorCompat

//    private lateinit var gameView: GameView
    private lateinit var squirrel: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = GamePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.settingsBtn.setOnClickListener { launchSettings() }
        binding.homeBtn.setOnClickListener { launchHome() }

        mDetector = GestureDetectorCompat(this, MyGestureListener())

//        val point = Point()
//        windowManager.defaultDisplay.getSize(point)
//
//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//        gameView = GameView(this, point.x, point.y)
//
//        setContentView(gameView)
        squirrel = findViewById(R.id.squirrel)
//        onTouchEvent(MotionEvent.)
    }
    private fun launchSettings() {
        listIntent = Intent(this, SettingsActivity::class.java)
        startActivity(listIntent)
    }
    private fun launchHome() {
        listIntent = Intent(this, MainActivity::class.java)
        startActivity(listIntent)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        mDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    private class MyGestureListener : GestureDetector.SimpleOnGestureListener() {

        override fun onDown(event: MotionEvent): Boolean {
            Log.d(DEBUG_TAG, "onDown: $event")
            return true
        }

        override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
            return true
        }
    }
//
//    override fun onPause() {
//        super.onPause()
//        gameView.pause()
//    }
//
//    override fun onResume() {
//        super.onResume()
//        gameView.resume()
//    }
}