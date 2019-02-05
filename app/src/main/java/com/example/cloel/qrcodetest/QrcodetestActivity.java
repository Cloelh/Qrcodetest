package com.example.cloel.qrcodetest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class QrcodetestActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcodetest);

        String texte = getPreferences(Context.MODE_PRIVATE).getString("dernier", "---");
        ((TextView) findViewById(R.id.textViewResult)).setText(texte);

    }



    private ZXingScannerView mScannerView;


    @Override
    public void onResume() {
        super.onResume();
        mScannerView = (ZXingScannerView) findViewById(R.id.camView);

        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        ((TextView) findViewById(R.id.textViewResult)).setText(rawResult.getText());

        SharedPreferences.Editor editor = getPreferences(Context.MODE_PRIVATE).edit();
        editor.putString("dernier", rawResult.getText());
        editor.commit();

        MapView mapView = findViewById(R.id.mapView);
        mapView.addPoint( rawResult.getText());


        mScannerView.resumeCameraPreview(this);
    }

    public void popup(View view){
            MapView mapView = findViewById(R.id.mapView);
            mapView.addPoint("abc");


            AlertDialog.Builder builder = new AlertDialog.Builder(this);

// 2. Chain together various setter methods to set the dialog characteristics
            builder.setMessage("Ca marche")
                    .setTitle("Pop up");

            // 3. Get the <code><a href="/reference/android/app/AlertDialog.html">AlertDialog</a></code> from <code><a href="/reference/android/app/AlertDialog.Builder.html#create()">create()</a></code>
            AlertDialog dialog = builder.create();
            dialog.show();

    }
}

