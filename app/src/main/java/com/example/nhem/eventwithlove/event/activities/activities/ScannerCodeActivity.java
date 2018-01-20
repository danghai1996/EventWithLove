package com.example.nhem.eventwithlove.event.activities.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.nhem.eventwithlove.R;
import com.example.nhem.eventwithlove.event.activities.Preferences;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannerCodeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView zXingScannerView;
    private boolean beforeClickPermissionRat;
    private boolean afterClickPermissionRat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        zXingScannerView = new ZXingScannerView(getApplicationContext());
        setContentView(zXingScannerView);
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }

    @Override
    public void handleResult(Result result) {
        Toast.makeText(this, result.getText(), Toast.LENGTH_SHORT).show();
        Log.d("test", "handleResult: " + result.toString());
        Preferences.getInstance().putEvent(result.toString());
        Log.d("test", "handleResult: " + Preferences.getInstance().getEvent() + " " + Preferences.getInstance().getToken());
        Intent intent = new Intent(getApplicationContext(), EventActivity.class);
        startActivity(intent);
        zXingScannerView.resumeCameraPreview(this);

    }
}
