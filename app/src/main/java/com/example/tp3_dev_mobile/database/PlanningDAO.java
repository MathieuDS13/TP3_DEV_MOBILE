package com.example.tp3_dev_mobile.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tp3_dev_mobile.Planning;

import java.util.List;

@Dao
public interface PlanningDAO {

    @Insert
    void insert(PlanningData... planning);

    @Delete
    void delete (PlanningData... planning);

    @Update
    void update(PlanningData... planning);

    @Query("SELECT * FROM planning")
    List<PlanningData> getPlanning();
}
