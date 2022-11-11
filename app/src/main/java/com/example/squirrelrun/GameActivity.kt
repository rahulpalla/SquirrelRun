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
import androidx.core.animation.doOnStart
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.GestureDetectorCompat
import com.example.squirrelrun.databinding.GamePageBinding
import kotlinx.coroutines.NonCancellable.start
import kotlin.properties.Delegates

private const val DEBUG_TAG = "Gestures"

class GameActivity : AppCompatActivity() {

    private lateinit var binding: GamePageBinding
    private lateinit var listIntent: Intent
    private lateinit var mDetector: GestureDetectorCompat

//    private lateinit var gameView: GameView
    private lateinit var squirrel: ImageView
    private lateinit var wolf: ImageView
    private lateinit var acorn: ImageView
    private var isPlaying by Delegates.notNull<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = GamePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.settingsBtn.setOnClickListener { launchSettings() }
        binding.homeBtn.setOnClickListener { launchHome() }

        mDetector = GestureDetectorCompat(this, MyGestureListener())
        isPlaying = true;
        squirrel = findViewById(R.id.squirrel)
        wolf = findViewById(R.id.wolf)
        acorn = findViewById(R.id.acorn)

        playGame()

    }

    private fun playGame() {
//        while (isPlaying) {
            movingWolf()
//        }
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
        //slides the wolf off the screen to the left
        //how to get it to pop back up?
        val animator = ObjectAnimator.ofFloat(wolf, "translationX", -4000f).apply {
            duration = 6000
            start()
        }
        animator.doOnStart { wolf.visibility = View.VISIBLE }
        animator.doOnEnd {
            wolf.visibility = View.GONE
            animator.start()
        }
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