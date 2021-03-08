package com.example.tp3_dev_mobile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.tp3_dev_mobile.database.MyAppDatabase;
import com.example.tp3_dev_mobile.database.User;
import com.example.tp3_dev_mobile.database.UserDAO;

import java.io.FileOutputStream;
import java.util.UUID;

public class RegisterActivity extends AppCompatActivity {

    private final static String NOM = "NOM";
    private final static String PRENOM = "PRENOM";
    private final static String AGE = "AGE";
    private final static String TEL = "TEL";
    private final static String ID = "ID";
    private final static String SAVE = "SAVE";

    String user_id;

    EditText editPrenom, editNom, editTel, editAge, editMDP;
    TextView unique_ID;
    Button button_valide, button_planning;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLifecycle().addObserver(new Utilisation());
        setContentView(R.layout.activity_register);

        MyAppDatabase db = Room.databaseBuilder(getApplicationContext(), MyAppDatabase.class, "database-name").allowMainThreadQueries().build();
        UserDAO userDAO = db.getUserDao();

        editNom = findViewById(R.id.form_nom);
        editPrenom = findViewById(R.id.form_prenom);
        editAge = findViewById(R.id.form_age);
        editTel = findViewById(R.id.form_phone);
        editMDP = findViewById(R.id.form_password);
        unique_ID = findViewById(R.id.unique_id);
        user_id = generateID();
        unique_ID.setText(user_id);
        button_valide = findViewById(R.id.validate_button);
        button_planning = findViewById(R.id.button_planning);

        button_valide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fos = openFileOutput(SAVE, Context.MODE_PRIVATE);
                    fos.write((editNom.getText().toString() + "/" + user_id).getBytes());
                    int nombreUtil = Utilisation.nombreUtilisation();
                    fos.write(nombreUtil);
                    Intent intent = new Intent(context, FileContentActivity.class);
                    intent.putExtra("file_name", SAVE);
                    intent.putExtra("utilisations", nombreUtil);

                    userDAO.insert(new User(user_id, editNom.getText().toString(), editPrenom.getText().toString(), editTel.getText().toString(), editAge.getText().toString()));

                    startActivity(intent);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        button_planning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlanningActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(NOM, editNom.getText().toString());
        savedInstanceState.putString(PRENOM, editPrenom.getText().toString());
        savedInstanceState.putString(AGE, editAge.getText().toString());
        savedInstanceState.putString(TEL, editTel.getText().toString());
        savedInstanceState.putString(ID, user_id);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        String nom = savedInstanceState.getString(NOM);
        String prenom = savedInstanceState.getString(PRENOM);
        String age = savedInstanceState.getString(AGE);
        String tel = savedInstanceState.getString(TEL);
        user_id = savedInstanceState.getString(ID);

        editPrenom.setText(prenom);
        editNom.setText(nom);
        editAge.setText(age);
        editTel.setText(tel);
        editMDP.setHint("Mot de passe");
        editMDP.setText("");
        unique_ID.setText(user_id);
    }

    private String generateID() {
        return UUID.randomUUID().toString();
    }
}