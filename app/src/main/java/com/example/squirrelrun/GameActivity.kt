package com.example.squirrelrun

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
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
import androidx.core.animation.doOnEnd
import androidx.core.view.GestureDetectorCompat
import com.example.squirrelrun.databinding.GamePageBinding
import kotlinx.coroutines.NonCancellable.start

private const val DEBUG_TAG = "Gestures"

class GameActivity : AppCompatActivity() {

    private lateinit var binding: GamePageBinding
    private lateinit var listIntent: Intent
    private lateinit var mDetector: GestureDetectorCompat

//    private lateinit var gameView: GameView
    private lateinit var squirrel: ImageView
    private lateinit var wolf: ImageView
    private lateinit var acorn: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = GamePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.settingsBtn.setOnClickListener { launchSettings() }
        binding.homeBtn.setOnClickListener { launchHome() }

        mDetector = GestureDetectorCompat(this, MyGestureListener())

        squirrel = findViewById(R.id.squirrel)
        wolf = findViewById(R.id.wolf)
        acorn = findViewById(R.id.acorn)

        movingWolf()

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
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                jump();
            }
        }
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

    private fun jump() {
        val animator = ObjectAnimator.ofFloat(squirrel, View.TRANSLATION_Y, -500f)
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        disableDuringAnimation(animator)
        animator.start()
    }

    private fun movingWolf() {
        //randomly pick a starting x-coord place and make the wolf appear there
        //slide it till just past the squirrel and once it hits that x coor then make it disapear
//        val animator = ObjectAnimator.ofFloat(wolf, View.TRANSLATION_X, -1000f)
        val animator = ObjectAnimator.ofFloat(wolf, "translationX", -4000f).apply {
            duration = 4000
            start()
        }

//        animator.repeatCount = 3
        animator.doOnEnd { wolf.visibility = View.GONE }
        disableDuringAnimation(animator)
        animator.start()
    }

    private fun disableDuringAnimation(animator: ObjectAnimator) {
        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
               //TODO: disable touch gesture
            }

            override fun onAnimationEnd(animation: Animator?) {
                //TODO: enable touch gesture
            }
        })
    }
}