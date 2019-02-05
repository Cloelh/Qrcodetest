package com.example.cloel.qrcodetest;

import android.graphics.Canvas;
import android.graphics.Point;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by amelie.chambon on 05/02/19.
 */

class PinStore {



    private Map<String, Sprite> point;




    public PinStore() {
        point =  new HashMap<>();
        point.put("abc", new Sprite(R.mipmap.pin,1000,500,0));
        point.put("def", new Sprite(R.mipmap.pin,1500,000,1));
        point.put("ghi", new Sprite(R.mipmap.pin,500,500,2));
    }

    public void set(String qrcode) {
        Sprite sprite = point.get(qrcode);
        if (sprite != null) sprite.visible = true;

    }

    public void paint(Canvas canvas) {
        for(Sprite s : point.values()){
            s.paint(canvas);
        }

    }
}
