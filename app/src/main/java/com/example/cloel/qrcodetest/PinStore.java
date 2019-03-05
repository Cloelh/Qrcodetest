package com.example.cloel.qrcodetest;

import android.content.Context;
import android.graphics.Canvas;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by amelie.chambon on 05/02/19.
 */

class PinStore {



    private Map<String, Sprite> point;




    public PinStore() {
        point =  new HashMap<>();
        point.put("abc", new Sprite(R.mipmap.pin,1000,500,0, "titre_abc", "contenu_abc"));
        point.put("def", new Sprite(R.mipmap.pin,1500,000,1, "titre_def", "contenu_def"));
        point.put("ghi", new Sprite(R.mipmap.pin,500,500,2, "titre_ghi", "contenu_abc"));
    }

    public Sprite set(String qrcode) {
        Sprite sprite = point.get(qrcode);
        if (sprite != null) sprite.visible = true;
        return sprite;
    }

    public void paint(Canvas canvas) {
        for(Sprite s : point.values()){
            s.paint(canvas);
        }

    }
}
