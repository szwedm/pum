package com.mm.bookmaker.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mm.bookmaker.models.Team;

import java.util.List;

@Dao
public interface TeamDao {

    @Query("SELECT * FROM team")
    List<Team> getAll();

    @Query("SELECT * FROM team WHERE id IN (:teamIds)")
    List<Team> loadAllByIds(Integer[] teamIds);

    @Query("SELECT * FROM team WHERE name LIKE :teamName LIMIT 1")
    Team findByName(String teamName);

    @Query("SELECT * FROM team WHERE id LIKE :id")
    Team findById(Integer id);
    @Query("SELECT * FROM team ORDER BY points DESC ")
    List<Team>getAllTeamsByPoints();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTeam(Team team);

    @Update
    void updateTeam(Team team);

    @Delete
    void deleteTeam(Team team);
}
