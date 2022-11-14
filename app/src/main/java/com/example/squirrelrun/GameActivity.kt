package com.example.squirrelrun

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.squirrelrun.databinding.GamePageBinding
import java.security.AccessController.getContext

import kotlin.random.Random


class GameActivity : AppCompatActivity() {

//    var context: Context? = null
//    var background: Bitmap? = null
//    var handler: Handler? = null
//    var screenWidth = 0
//    var screenHeight = 0
//    var points = 0
//    var life = 1
//    var isPlaying = true
//    var scorePaint: Paint? = null
//    var textSize = 150
//    var paused = false
//    var squirrel: Squirrel? = null
//    var random: Random? = null
//    var acorns: ArrayList<Acorn>? = null
//    var wolves: ArrayList<Wolf>? = null
//    var wolfFalling = false
//    var acornFalling = false

    private lateinit var binding: GamePageBinding
    private lateinit var listIntent: Intent

    private val runnable = Runnable {
        //invalidate
        binding.root.invalidate()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = GamePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.settingsBtn.setOnClickListener { launchSettings() }
        binding.homeBtn.setOnClickListener { launchHome() }

//        val display = (getContext() as Activity).windowManager.defaultDisplay
//        val size = Point()
//        display.getSize(size)
//        screenWidth = size.x
//        screenHeight = size.y
//        random = Random
//        wolves = ArrayList()
//        acorns = ArrayList()
//        squirrel = Squirrel(context)
//        handler = Handler()
//        background = BitmapFactory.decodeResource(context!!.resources, R.drawable.background_home)
//        scorePaint = Paint()
//        scorePaint!!.color = Color.BLACK
//        scorePaint!!.textSize = textSize.toFloat()
//        scorePaint!!.textAlign = Paint.Align.LEFT

        setContentView(GameView(this))
    }

    private fun launchSettings() {
        listIntent = Intent(this, SettingsActivity::class.java)
        startActivity(listIntent)
    }
    private fun launchHome() {
        listIntent = Intent(this, MainActivity::class.java)
        startActivity(listIntent)
    }

//    protected fun onDraw(canvas: Canvas) {
//        canvas.drawBitmap(background!!, 0f, 0f, null)
//        canvas.drawText("Points: $points", 0f, textSize.toFloat(), scorePaint!!)
//        if (!isPlaying) {
//            //launch game over screen
//            paused = true
//            handler = null
//            val intent = Intent(context, GameOverActivity::class.java)
//            intent.putExtra("points", points)
//            context!!.startActivity(intent)
//            (context as Activity).finish()
//        }
//        if (!wolfFalling) {
//            val wolf = Wolf(context, random!!.nextInt(1200), 0)
//            wolves!!.add(wolf)
//            wolfFalling = true
//        }
//        if (!acornFalling) {
//            val acorn = Acorn(context, random!!.nextInt(1200), 0)
//            acorns!!.add(acorn)
//            acornFalling = true
//        }
//        canvas.drawBitmap(
//            squirrel!!.getSquirrel(),
//            squirrel!!.x.toFloat(),
//            squirrel!!.y.toFloat(),
//            null
//        )
//        for (i in 0 until wolves.size) {
//            wolves!![i].y += 15
//            canvas.drawBitmap(
//                wolves!![i].shot,
//                wolves!![i].x.toFloat(),
//                wolves!![i].y.toFloat(),
//                null
//            )
//            if (wolves!![i].x >= squirrel!!.x
//                && wolves!![i].x <= squirrel!!.x + squirrel!!.width && wolves!![i].y >= squirrel!!.y && wolves!![i].y <= GameView.screenHeight
//            ) {
//                isPlaying = false
//
//                //launch game over screen
//                paused = true
//                handler = null
//                val intent = Intent(context, GameOverActivity::class.java)
//                intent.putExtra("points", points)
//                context!!.startActivity(intent)
//                (context as Activity).finish()
//
//                wolves!!.removeAt(i)
//            } else if (wolves!![i].y >= GameView.screenHeight) {
//                wolves!!.removeAt(i)
//            }
//            if (wolves!!.size < 1) {
//                wolfFalling = false
//            }
//        }
//        for (i in 0 until acorns.size) {
//            acorns!![i].y += 15
//            canvas.drawBitmap(
//                acorns!![i].shot,
//                acorns!![i].x.toFloat(),
//                acorns!![i].y.toFloat(),
//                null
//            )
//            if (acorns!![i].x >= squirrel!!.x
//                && acorns!![i].x <= squirrel!!.x + squirrel!!.width && acorns!![i].y >= squirrel!!.y && acorns!![i].y <= GameView.screenHeight
//            ) {
//                points++
//                acorns!!.removeAt(i)
//            } else if (acorns!![i].y >= GameView.screenHeight) {
//                acorns!!.removeAt(i)
//            }
//            if (acorns!!.size < 1) {
//                acornFalling = false
//            }
//        }
//        if (!paused) handler!!.postDelayed(runnable, 30)
//    }
//
//    override fun onTouchEvent(event: MotionEvent): Boolean {
//        val temp = event.x.toInt()
//        if (event.action == MotionEvent.ACTION_DOWN) {
//            squirrel!!.x = temp
//        }
//        if (event.action == MotionEvent.ACTION_MOVE) {
//            squirrel!!.x = temp
//        }
//        return true
//    }
}

