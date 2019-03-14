package com.example.cloel.qrcodetest;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class QrcodetestActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private static final int MY_PERMISSIONS_CAMERA = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcodetest);

        String texte = getPreferences(Context.MODE_PRIVATE).getString("dernier", "---");
        ((TextView) findViewById(R.id.textViewResult)).setText(texte);

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        MY_PERMISSIONS_CAMERA);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
        }



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
        Sprite sprite = mapView.addPoint( rawResult.getText());
//        PinStore pinStore = mapView.pinStore;
        if(sprite != null){
            sprite.pop(this);
//            mapView.newPop(this,sprite);
        }
        mScannerView.resumeCameraPreview(this);
    }

    public void popup(View view){
            MapView mapView = findViewById(R.id.mapView);


            AlertDialog.Builder builder = new AlertDialog.Builder(this);

// 2. Chain together various setter methods to set the dialog characteristics
            builder.setMessage("Ca marche")
                    .setTitle("Pop up");

            // 3. Get the <code><a href="/reference/android/app/AlertDialog.html">AlertDialog</a></code> from <code><a href="/reference/android/app/AlertDialog.Builder.html#create()">create()</a></code>
            AlertDialog dialog = builder.create();
            dialog.show();

    }
}

