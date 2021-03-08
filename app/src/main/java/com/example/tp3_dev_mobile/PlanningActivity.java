package com.example.tp3_dev_mobile;

import android.app.Application;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

public class PlanningActivity extends AppCompatActivity {

    TextView horaire1, horaire2, horaire3, horaire4;
    Planning planning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_planning);

        horaire1 = findViewById(R.id.horaire1);
        horaire2 = findViewById(R.id.horaire2);
        horaire3 = findViewById(R.id.horaire3);
        horaire4 = findViewById(R.id.horaire4);

        planning = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create((Planning.class));

        Observer<String[]> observer = new Observer<String[]>() {
            @Override
            public void onChanged(String[] strings) {
                horaire1.setText(strings[0]);
                horaire2.setText(strings[1]);
                horaire3.setText(strings[2]);
                horaire4.setText(strings[3]);
            }
        };

        planning.getHoraires().observe(this, observer);

        //MutableLiveData<String>[] horaires = planning.getHoraires();


    }
}
