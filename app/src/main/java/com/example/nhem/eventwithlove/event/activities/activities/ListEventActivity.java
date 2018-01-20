package com.example.nhem.eventwithlove.event.activities.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhem.eventwithlove.R;

public class ListEventActivity extends AppCompatActivity {
    ImageView ivQRcode;
    ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_event);

        setupUI();

        ivQRcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListEventActivity.this, QRActvity.class);
                startActivity(intent);
//                finish();
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setupUI() {
        ivQRcode = findViewById(R.id.iv_qrcode);
        ivBack = findViewById(R.id.iv_back);
    }
}
