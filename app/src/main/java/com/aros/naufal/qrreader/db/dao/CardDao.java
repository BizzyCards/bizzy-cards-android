package com.aros.naufal.qrreader.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.aros.naufal.qrreader.db.entity.Card;

import java.util.List;

@Dao
public interface CardDao {

    @Insert
    void insert(Card card);

    @Query("DELETE FROM card_table")
    void deleteAll();

    @Query("SELECT * from card_table ORDER BY name ASC")
    LiveData<List<Card>> getAllCards();
}
