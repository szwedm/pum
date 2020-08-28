package com.mm.bookmaker.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mm.bookmaker.models.Match;
import com.mm.bookmaker.models.Player;
import com.mm.bookmaker.models.Team;

@Database(entities = {Player.class, Team.class, Match.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract PlayerDao playerDao();
    public abstract TeamDao teamDao();
    public abstract MatchDao matchDao();

    private static AppDatabase appDb;

    public static AppDatabase getInstance(Context context) {
        if (appDb == null) appDb = buildDatabaseInstance(context);
        return appDb;
    }

    private static AppDatabase buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class, "Bookmaker.db").allowMainThreadQueries().build();
    }
}
