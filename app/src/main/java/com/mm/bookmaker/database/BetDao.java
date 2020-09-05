package com.mm.bookmaker.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.mm.bookmaker.models.Bet;

import java.util.List;

@Dao
public interface BetDao {

    @Query("SELECT * FROM bet")
    List<Bet> getAll();

    @Query("SELECT * FROM bet WHERE match_id == :matchId")
    Bet findByMatchId(Integer matchId);

    @Query("SELECT * FROM bet WHERE status == :status")
    List<Bet> getBetsByStatus(String status);

    @Insert
    void insertBet(Bet bet);

    @Update
    void updateBet(Bet bet);

    @Delete
    void deleteBet(Bet bet);
}
