package com.example.squirrelrun;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class GameView extends View {
    Context context;
    Bitmap background, lifeImage;
    Handler handler;
    long UPDATE_MILLIS = 30;
    static int screenWidth, screenHeight;
    int points = 0;
    int life = 3;
    Paint scorePaint;
    int TEXT_SIZE = 100;
    boolean paused = false;
    Squirrel squirrel;
//    EnemySpaceship enemySpaceship;
    Random random;
    ArrayList<Acorn> acorns;
    ArrayList<Wolf> wolves;
//    Explosion explosion;
//    ArrayList<Explosion> explosions;
    boolean wolfFalling = false;
    boolean acornFalling = false;
    final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };


    public GameView(Context context) {
        super(context);
        this.context = context;
        Display display = ((Activity) getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        random = new Random();
        wolves = new ArrayList<>();
//        ourShots = new ArrayList<>();
        acorns = new ArrayList<>();
//        explosions = new ArrayList<>();
        squirrel = new Squirrel(context);
//        enemySpaceship = new EnemySpaceship(context);
        handler = new Handler();
        background = BitmapFactory.decodeResource(context.getResources(), R.drawable.background_home);
        lifeImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.life);
        scorePaint = new Paint();
        scorePaint.setColor(Color.BLACK);
        scorePaint.setTextSize(TEXT_SIZE);
        scorePaint.setTextAlign(Paint.Align.LEFT);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Draw background, Points and life on Canvas
        canvas.drawBitmap(background, 0, 0, null);
        canvas.drawText("Pt: " + points, 0, TEXT_SIZE, scorePaint);
        for(int i=life; i>=1; i--){
            canvas.drawBitmap(lifeImage, screenWidth - lifeImage.getWidth() * i, 0, null);
        }
        // When life becomes 0, stop game and launch GameOver Activity with points
//        if(life == 0){
//            paused = true;
//            handler = null;
//            Intent intent = new Intent(context, GameOver.class);
//            intent.putExtra("points", points);
//            context.startActivity(intent);
//            ((Activity) context).finish();
//        }

        if (wolfFalling == false) {
            Wolf wolf = new Wolf(context, random.nextInt(1200), 0);
            wolves.add(wolf);
            wolfFalling = true;

        }
        if (acornFalling == false) {
            Acorn acorn = new Acorn(context, random.nextInt(1200), 0);
            acorns.add(acorn);
            acornFalling = true;
        }

        canvas.drawBitmap(squirrel.getSquirrel(), squirrel.ox, squirrel.oy, null);

        for(int i=0; i < wolves.size(); i++){
            wolves.get(i).shy += 15;
            canvas.drawBitmap(wolves.get(i).getShot(), wolves.get(i).shx, wolves.get(i).shy, null);
            if((wolves.get(i).shx >= squirrel.ox)
                    && wolves.get(i).shx <= squirrel.ox + squirrel.getWidth()
                    && wolves.get(i).shy >= squirrel.oy
                    && wolves.get(i).shy <= screenHeight){
                life--;
                if(life == 0){
                    paused = true;
                    handler = null;
                    Intent intent = new Intent(context, GameOverActivity.class);
                    intent.putExtra("points", points);
                    context.startActivity(intent);
                    ((Activity) context).finish();
                }
                wolves.remove(i);
            }else if(wolves.get(i).shy >= screenHeight){
                wolves.remove(i);
            }
            if(wolves.size() < 1){
                wolfFalling = false;
            }
        }

        for(int i=0; i < acorns.size(); i++){
            acorns.get(i).shy += 15;
            canvas.drawBitmap(acorns.get(i).getShot(), acorns.get(i).shx, acorns.get(i).shy, null);
            if ((acorns.get(i).shx >= squirrel.ox)
                    && acorns.get(i).shx <= squirrel.ox + squirrel.getWidth()
                    && acorns.get(i).shy >= squirrel.oy
                    && acorns.get(i).shy <= screenHeight) {
                points++;
                acorns.remove(i);
            } else if (acorns.get(i).shy >= screenHeight) {
                acorns.remove(i);
            }
            if(acorns.size() < 1){
                acornFalling = false;
            }
        }

        // If not paused, we’ll call the postDelayed() method on handler object which will cause the
        // run method inside Runnable to be executed after 30 milliseconds, that is the value inside
        // UPDATE_MILLIS.
        if(!paused)
            handler.postDelayed(runnable, UPDATE_MILLIS);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int touchX = (int)event.getX();
        // When event.getAction() is MotionEvent.ACTION_DOWN, control ourSpaceship
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            squirrel.ox = touchX;
        }
        // When event.getAction() is MotionEvent.ACTION_MOVE, control ourSpaceship
        // along with the touch.
        if(event.getAction() == MotionEvent.ACTION_MOVE){
            squirrel.ox = touchX;
        }
        // Returning true in an onTouchEvent() tells Android system that you already handled
        // the touch event and no further handling is required.
        return true;
    }
}

