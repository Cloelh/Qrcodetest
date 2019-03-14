package com.example.cloel.qrcodetest;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

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

    protected String titre;
    protected String contenu;


    Sprite(int id, float x, float y, int state, String titre, String contenu) {
        this.sprite = SpriteSheet.get(id);
        this.x = x;
        this.y = y;
        this.state = state;
        visible = false;
        this.titre = titre;
        this.contenu = contenu;
    }

    public void paint(Canvas canvas) {

        if (visible) sprite.paint(canvas, state, x, y);
    }

    public void pop(Context context) {

        if (visible) {


            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            // 2. Chain together various setter methods to set the dialog characteristics
            builder.setMessage(contenu)
                    .setTitle(titre);

            // 3. Get the <code><a href="/reference/android/app/AlertDialog.html">AlertDialog</a></code> from <code><a href="/reference/android/app/AlertDialog.Builder.html#create()">create()</a></code>
            AlertDialog dialog = builder.create();
            dialog.show();
        };
    }
}

