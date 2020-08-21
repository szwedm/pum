package com.mm.bookmaker.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.mm.bookmaker.models.Match;
import com.mm.bookmaker.models.Player;
import com.mm.bookmaker.models.Team;

@Database(entities = {Player.class, Team.class, Match.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract PlayerDao playerDao();
    public abstract TeamDao teamDao();
    public abstract MatchDao matchDao();
}
