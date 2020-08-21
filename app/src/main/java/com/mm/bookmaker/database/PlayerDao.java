package com.mm.bookmaker.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.mm.bookmaker.models.Player;

import java.util.List;

@Dao
public interface PlayerDao {

    @Query("SELECT * FROM player")
    List<Player> getAll();

    @Query("SELECT * FROM player WHERE id IN (:playerIds)")
    List<Player> loadAllByIds(Long[] playerIds);

    @Query("SELECT * FROM player WHERE first_name LIKE :firstName AND " +
            "last_name LIKE :lastName LIMIT 1")
    Player findByName(String firstName, String lastName);

    @Insert
    void insertAll(Player... players);

    @Delete
    void delete(Player player);
}