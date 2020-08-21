package com.mm.bookmaker.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.mm.bookmaker.models.Team;

import java.util.List;

@Dao
public interface TeamDao {

    @Query("SELECT * FROM team")
    List<Team> getAll();

    @Query("SELECT * FROM team WHERE id IN (:teamIds)")
    List<Team> loadAllByIds(Long[] teamIds);

    @Query("SELECT * FROM team WHERE name LIKE :teamName LIMIT 1")
    Team findByName(String teamName);

    @Insert
    void insertAll(Team... teams);

    @Delete
    void delete(Team team);
}
