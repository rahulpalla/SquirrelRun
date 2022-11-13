package com.example.squirrelrun;

import android.app.Activity;
import android.content.Context;
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
    Bitmap background;
    Handler handler;
    static int screenWidth, screenHeight;
    int points = 0;
    boolean isPlaying = true;
    Paint scorePaint;
    int TEXT_SIZE = 100;
    boolean paused = false;
    Squirrel squirrel;
    Random random;
    ArrayList<Acorn> acorns;
    ArrayList<Wolf> wolves;
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
        acorns = new ArrayList<>();
        squirrel = new Squirrel(context);
        handler = new Handler();
        background = BitmapFactory.decodeResource(context.getResources(), R.drawable.background_home);
        scorePaint = new Paint();
        scorePaint.setColor(Color.BLACK);
        scorePaint.setTextSize(TEXT_SIZE);
        scorePaint.setTextAlign(Paint.Align.LEFT);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(background, 0, 0, null);
        canvas.drawText("Points: " + points, 0, TEXT_SIZE, scorePaint);

        if (!isPlaying) {
            //launch game over screen
        }

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

        canvas.drawBitmap(squirrel.getSquirrel(), squirrel.x, squirrel.y, null);

        for (int i=0; i < wolves.size(); i++){
            wolves.get(i).y += 15;
            canvas.drawBitmap(wolves.get(i).getShot(), wolves.get(i).x, wolves.get(i).y, null);
            if ((wolves.get(i).x >= squirrel.x)
                    && wolves.get(i).x <= squirrel.x + squirrel.getWidth()
                    && wolves.get(i).y >= squirrel.y
                    && wolves.get(i).y <= screenHeight){
                isPlaying = false;
                wolves.remove(i);
            } else if(wolves.get(i).y >= screenHeight){
                wolves.remove(i);
            }
            if(wolves.size() < 1){
                wolfFalling = false;
            }
        }

        for (int i=0; i < acorns.size(); i++){
            acorns.get(i).y += 15;
            canvas.drawBitmap(acorns.get(i).getShot(), acorns.get(i).x, acorns.get(i).y, null);
            if ((acorns.get(i).x >= squirrel.x)
                    && acorns.get(i).x <= squirrel.x + squirrel.getWidth()
                    && acorns.get(i).y >= squirrel.y
                    && acorns.get(i).y <= screenHeight) {
                points++;
                acorns.remove(i);
            } else if (acorns.get(i).y >= screenHeight) {
                acorns.remove(i);
            }
            if (acorns.size() < 1){
                acornFalling = false;
            }
        }

        if (!paused)
            handler.postDelayed(runnable, 30);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int temp = (int)event.getX();
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            squirrel.x = temp;
        }
        if(event.getAction() == MotionEvent.ACTION_MOVE){
            squirrel.x = temp;
        }
        return true;
    }
}

