package com.example.tp3_dev_mobile.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDAO {

    @Insert
    void insert(User... user);

    @Delete
    void delete(User... user);

    @Update
    void update(User... user);

    @Query("SELECT * FROM USER WHERE id = :userID ")
    User getUserByID(String userID);
}
