package com.example.squirrelrun;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Wolf {
    Bitmap wolf;
    Bitmap scaler;
    Context context;
    int shx, shy;

    public Wolf(Context context, int shx, int shy) {
        this.context = context;

        scaler = BitmapFactory.decodeResource(context.getResources(), R.drawable.wolf);

        wolf = Bitmap.createScaledBitmap(scaler, 200, 200, false);
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