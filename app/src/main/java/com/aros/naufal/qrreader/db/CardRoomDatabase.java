package com.aros.naufal.qrreader.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.aros.naufal.qrreader.db.dao.CardDao;
import com.aros.naufal.qrreader.db.entity.Card;

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
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final CardDao mDao;

        PopulateDbAsync(CardRoomDatabase db) {
            mDao = db.cardDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            Log.d("CardRoomDatabase", "Populating database");
            mDao.deleteAll();
            Card card = new Card("Naufal Aros");
            mDao.insert(card);
            card = new Card("Miguel Rojo");
            mDao.insert(card);
            return null;
        }
    }
}
