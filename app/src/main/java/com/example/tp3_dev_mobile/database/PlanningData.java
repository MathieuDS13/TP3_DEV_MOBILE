package com.example.tp3_dev_mobile.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "planning")
public class PlanningData {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "crenaux_1")
    public String crenaux1;

    @ColumnInfo(name = "crenaux_2")
    public String crenaux2;

    @ColumnInfo(name = "crenaux_3")
    public String crenaux3;

    @ColumnInfo(name = "crenaux_4")
    public String crenaux4;

    public PlanningData( String crenaux1, String crenaux2, String crenaux3, String crenaux4) {
        this.crenaux1 = crenaux1;
        this.crenaux2 = crenaux2;
        this.crenaux3 = crenaux3;
        this.crenaux4 = crenaux4;
    }
}
