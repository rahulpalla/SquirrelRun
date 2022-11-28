package com.example.squirrelrun;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class Acorn {
    Bitmap acorn;
    Bitmap scaler;
    Context context;
    int x, y;

    public Acorn(Context context, int otherx, int othery) {
        this.context = context;
        scaler = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.acorn_img);
        acorn = Bitmap.createScaledBitmap(scaler, (int) (GameView.screenHeight * 0.138), (int) (GameView.screenHeight * 0.138), false);
        this.x = otherx;
        this.y = othery;
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
