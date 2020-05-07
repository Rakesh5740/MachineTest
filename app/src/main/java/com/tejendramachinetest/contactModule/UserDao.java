package com.tejendramachinetest.contactModule;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.tejendramachinetest.Constant;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM " + Constant.Contacts)
    List<User> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<User> users);

    @Delete
    void delete(User user);

    // allowing the insert of the same word multiple times by passing a

    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(User word);

}
