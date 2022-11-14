package com.example.squirrelrun;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class Squirrel {
    Context context;
    Bitmap scaler;
    Bitmap squirrel;
    int x, y;
    Random random;

    public Squirrel(Context context) {
        this.context = context;
        scaler = BitmapFactory.decodeResource(context.getResources(), R.drawable.squirrel_img);
        squirrel = Bitmap.createScaledBitmap(scaler, 300, 300, false);
        random = new Random();
        x = random.nextInt(GameView.screenWidth);
        y = GameView.screenHeight - squirrel.getHeight();
    }

    public Bitmap getSquirrel(){
        return squirrel;
    }

    int getWidth(){
        return squirrel.getWidth();
    }
}
