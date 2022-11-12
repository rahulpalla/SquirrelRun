package com.example.squirrelrun;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class Acorn {
    Bitmap acorn;
    Bitmap scaler;
    Context context;
    int shx, shy;

    public Acorn(Context context, int shx, int shy) {
        this.context = context;
        scaler = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.acorn_img);
        acorn = Bitmap.createScaledBitmap(scaler, 150, 150, false);
        this.shx = shx;
        this.shy = shy;
    }
    public Bitmap getShot(){
        return acorn;
    }
    public int getShotWidth() {
        return acorn.getWidth();
    }
    public int getShotHeight() {
        return acorn.getHeight();
    }
}
