package com.example.attendance_nfc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.attendance_nfc.NFC.ScanTag;
import com.example.attendance_nfc.NFC.SearchNim;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonTag = (Button) findViewById(R.id.buttonTag);
        Button buttonNim = (Button) findViewById(R.id.buttonNim);
        Button buttonName = (Button) findViewById(R.id.buttonName);
        Button buttonEvent = (Button) findViewById(R.id.buttonEvent);

        buttonTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity_scan_tag();
            }
        });
        buttonNim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openactivity_search_nim();
            }
        });

    }

    private void openactivity_search_nim() {
        Intent intent = new Intent(this, SearchNim.class);
        startActivity(intent);
    }

    private void openactivity_scan_tag() {
        Intent i = new Intent(this, ScanTag.class);
        startActivity(i);
    }
}