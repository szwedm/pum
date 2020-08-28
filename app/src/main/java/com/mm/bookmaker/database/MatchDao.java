package com.mm.bookmaker.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mm.bookmaker.models.Match;

import java.util.List;

@Dao
public interface MatchDao {

    @Query("SELECT * FROM `match`")
    List<Match> getAll();

    @Query("SELECT * FROM `match` WHERE id IN (:matchIds)")
    List<Match> loadAllByIds(Integer[] matchIds);

    @Query("SELECT * FROM `match` WHERE date LIKE :matchDate")
    Match findByDate(String matchDate);

    @Query("SELECT * FROM `match` WHERE id LIKE :id")
    Match findById(Integer id);

    @Insert
    void insertMatch(Match... matches);

    @Update
    void updateMatch(Match... matches);

    @Delete
    void deleteMatch(Match... matches);
}
