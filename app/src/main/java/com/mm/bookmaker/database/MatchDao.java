package com.mm.bookmaker.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mm.bookmaker.models.Match;

import java.time.LocalDate;
import java.util.List;

@Dao
public interface MatchDao {

    @Query("SELECT * FROM `match`")
    List<Match> getAll();

    @Query("SELECT * FROM `match` WHERE id IN (:matchIds)")
    List<Match> loadAllByIds(Long[] matchIds);

    @Query("SELECT * FROM `match` WHERE date LIKE :matchDate")
    Match findByDate(LocalDate matchDate);

    @Query("SELECT * FROM `match` WHERE id LIKE :id")
    Match findById(Long id);

    @Insert
    public void insertMatch(Match... matches);

    @Update
    public void updateMatch(Match... matches);

    @Delete
    public void deleteMatch(Match... matches);
}
