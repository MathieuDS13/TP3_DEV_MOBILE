package com.example.tp3_dev_mobile;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;

public class FileContentActivity extends AppCompatActivity {

    TextView display;
    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_file);
        display = findViewById(R.id.file_display);
        Bundle extras = getIntent().getExtras();
        String fileName = extras.getString("file_name");
        int utilisation = extras.getInt("utilisations");

        try {
            FileInputStream is = openFileInput(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String content = reader.readLine();
            display.setText(content + " Utilisations : " + utilisation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, " Owner onResume");

    }
}
