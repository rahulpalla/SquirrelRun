package com.example.squirrelrun;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.Display;

import androidx.appcompat.app.AppCompatActivity;

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
        squirrel = Bitmap.createScaledBitmap(scaler, (int) (GameView.screenHeight * 0.277), (int) (GameView.screenHeight * 0.277), false);
        random = new Random();
        x = random.nextInt(GameView.screenWidth);
        y = (int) (GameView.screenHeight - squirrel.getHeight() - GameView.screenHeight * 0.1);
    }

    public Bitmap getSquirrel(){
        return squirrel;
    }

    int getWidth(){
        return squirrel.getWidth();
    }
}
