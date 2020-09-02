package com.mm.bookmaker.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "bet")
public class Bet implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Integer id;

    @ColumnInfo(name = "match_id")
    private Integer match_id;

    @ColumnInfo(name = "value")
    private Integer value;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "status")
    private String status;

    @Ignore
    private Match match;

    public Bet(Integer match_id, Integer value, String type, String status) {
        this.match_id = match_id;
        this.value = value;
        this.type = type;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMatch_id() {
        return match_id;
    }

    public void setMatch_id(Integer match_id) {
        this.match_id = match_id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }
}
