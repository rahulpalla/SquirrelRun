package com.example.squirrelrun;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Wolf {
    Bitmap wolf;
    Bitmap scaler;
    Context context;
    int x, y;

    public Wolf(Context context, int shx, int shy) {
        this.context = context;
        scaler = BitmapFactory.decodeResource(context.getResources(), R.drawable.wolf2);


        wolf = Bitmap.createScaledBitmap(scaler, 150, 150, false);
        this.x = shx;
        this.y = shy;
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