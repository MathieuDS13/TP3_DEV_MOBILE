package com.example.tp3_dev_mobile.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, PlanningData.class}, version = 1)
public abstract class MyAppDatabase extends RoomDatabase {

    public abstract UserDAO getUserDao();

    public abstract PlanningDAO getPlanningDao();
}
