package com.example.cloel.qrcodetest;

import android.graphics.Canvas;
import android.graphics.RectF;

import com.example.cloel.qrcodetest.utils.SpriteSheet;


/**
 * Created by dubois on 05/12/2018.
 */

 class Sprite {

    protected final SpriteSheet sprite;
    protected int state;

    protected float x;
    protected float y;
    public boolean visible;


    Sprite(int id, float x, float y) {
        this.sprite = SpriteSheet.get(id);
        this.x = x;
        this.y = y;
        state = 0;
        visible = false;
    }

    public void paint(Canvas canvas) {

        if (visible) sprite.paint(canvas, state, x, y);
    }
}

