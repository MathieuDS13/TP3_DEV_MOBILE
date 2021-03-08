package com.example.tp3_dev_mobile;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.tp3_dev_mobile.database.MyAppDatabase;
import com.example.tp3_dev_mobile.database.PlanningDAO;
import com.example.tp3_dev_mobile.database.PlanningData;
import com.example.tp3_dev_mobile.database.UserDAO;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;

public class Planning extends AndroidViewModel { //AndroidViewModel à la place de ViewModel afin de pouvoir avoir accès au context ce qui simplifie la lecture de fichier
    String horaire1, horaire2, horaire3, horaire4;
    MutableLiveData<String[]> horaires;
    private final String filename = "planning";
    Application app;
    private Handler handler;
    MyAppDatabase db;
    PlanningDAO planningDAO;

    public Planning(@NonNull Application application) {
        super(application);
        this.app = application;
        db = Room.databaseBuilder(application.getApplicationContext(), MyAppDatabase.class, "database-name").allowMainThreadQueries().build();
        planningDAO = db.getPlanningDao();
        horaires = new MutableLiveData<String[]>();
        handler = new Handler();
        generateHoraires();
        Runnable getResponceAfterInterval = new Runnable() {

            public void run() {

                retrieveFromDB();
                handler.postDelayed(this, 5000); // 5000 -> toutes les 5 secondes pour les tests mais peut être remplacé pour être toutes les 24heures
                //Peut également être remplacé par un appel à l'API AlarmManager pour faire la mise à jour même avec l'application fermée

            }
        };
        handler.post(getResponceAfterInterval);
    }

    public MutableLiveData<String[]> getHoraires() {
        if (horaires == null) {
            horaires = new MutableLiveData<String[]>();
            retrieveFromDB();
        }
        return horaires;
    }

    private void generateHoraires() {
        try {
            System.out.println("Génération des données");
            Context context = app.getApplicationContext();
            context.openFileOutput(filename, Context.MODE_PRIVATE);
            InputStream is = context.getResources().openRawResource(R.raw.planning);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] newHoraires = line.trim().split(";");
                if(newHoraires.length == 4) planningDAO.insert(new PlanningData(newHoraires[0], newHoraires[1], newHoraires[2], newHoraires[3]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void retrieveFromDB() {
        List<PlanningData> plannings = planningDAO.getPlanning();
        Random rand = new Random();
        int tirage = rand.nextInt(plannings.size());
        String[] newHoraire = new String[4];
        newHoraire[0] = plannings.get(tirage).crenaux1;
        newHoraire[1] = plannings.get(tirage).crenaux2;
        newHoraire[2] = plannings.get(tirage).crenaux3;
        newHoraire[3] = plannings.get(tirage).crenaux4;

        horaires.setValue(newHoraire);
    }
}
