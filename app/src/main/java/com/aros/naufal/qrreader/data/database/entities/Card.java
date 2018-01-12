package com.aros.naufal.qrreader.data.database.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "card_table")
public class Card {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    private String name;



    public Card(String name) {
        this.name = name;
        //this.own = own;
    }

    public String getName() {
        return this.name;
    }

}
