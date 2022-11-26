package com.example.squirrelrun;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;

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

//        DisplayMetrics dm = new DisplayMetrics();`  `
//        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
//        float densityScale = dm.density;
//        float scaledWidth = 300 * densityScale;
//        float scaledHeight = 300 * densityScale;

//        squirrel = Bitmap.createScaledBitmap(scaler, (int)scaledWidth, (int)scaledHeight, false);


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
