package com.mm.bookmaker.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

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

    @Insert
    void insertAll(Match... matches);

    @Delete
    void delete(Match match);
}
