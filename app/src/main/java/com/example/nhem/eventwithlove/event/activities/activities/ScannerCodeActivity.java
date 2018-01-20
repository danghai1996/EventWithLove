package com.example.nhem.eventwithlove.event.activities.activities;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.nhem.eventwithlove.R;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannerCodeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView zXingScannerView;
    private boolean beforeClickPermissionRat;
    private boolean afterClickPermissionRat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, 50);
        }
        zXingScannerView = new ZXingScannerView(getApplicationContext());
        setContentView(zXingScannerView);
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
    }


    @Override
    public void handleResult(Result result) {
        Toast.makeText(this, result.getText(), Toast.LENGTH_SHORT).show();
        zXingScannerView.resumeCameraPreview(this);
    }
}
