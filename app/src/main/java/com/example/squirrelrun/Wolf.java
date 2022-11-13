package com.example.squirrelrun;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class Wolf {
    Bitmap wolf;
    Bitmap scaler;
    Context context;
    int shx, shy;

    public Wolf(Context context, int shx, int shy) {
        this.context = context;
        Random rand = new Random();
        int type = rand.nextInt(2);
        scaler = BitmapFactory.decodeResource(context.getResources(), R.drawable.wolf2);

        wolf = Bitmap.createScaledBitmap(scaler, 150, 150, false);
        this.shx = shx;
        this.shy = shy;
    }
    public Bitmap getShot(){
        return wolf;
    }
    public int getShotWidth() {
        return wolf.getWidth();
    }
    public int getShotHeight() {
        return wolf.getHeight();
    }
}