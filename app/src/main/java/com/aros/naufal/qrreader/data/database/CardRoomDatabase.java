package com.aros.naufal.qrreader.data.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.aros.naufal.qrreader.data.database.daos.CardDao;
import com.aros.naufal.qrreader.data.database.entities.Card;

@Database(entities = {Card.class}, version = 1)
public abstract class CardRoomDatabase extends RoomDatabase {

    public abstract CardDao cardDao();

    private static CardRoomDatabase INSTANCE;

    public static CardRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CardRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CardRoomDatabase.class, "card_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
