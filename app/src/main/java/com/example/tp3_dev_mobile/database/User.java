package com.example.tp3_dev_mobile.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {

    @PrimaryKey
    @NonNull
    public String id;

    @ColumnInfo(name = "user_nom")
    public String nom;

    @ColumnInfo(name = "user_prenom")
    public String prenom;

    @ColumnInfo(name = "user_phone")
    public String phone;

    @ColumnInfo(name = "user_age")
    public String age;

    public User(String id, String nom, String prenom, String phone, String age) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.phone = phone;
        this.age = age;
    }
}
