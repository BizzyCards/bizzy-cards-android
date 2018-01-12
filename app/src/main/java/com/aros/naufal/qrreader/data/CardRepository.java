package com.aros.naufal.qrreader.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.aros.naufal.qrreader.data.database.CardRoomDatabase;
import com.aros.naufal.qrreader.data.database.daos.CardDao;
import com.aros.naufal.qrreader.data.database.entities.Card;

import java.util.List;

public class CardRepository {

    private CardDao mCardDao;
    private LiveData<List<Card>> mAllCards;
    private LiveData<Card> m1Cards;

    public CardRepository(Application application) {
        CardRoomDatabase cardRoomDatabase = CardRoomDatabase.getDatabase(application);
        mCardDao = cardRoomDatabase.cardDao();
        mAllCards = mCardDao.getAllCards();
    }

    public LiveData<List<Card>> getAllCards() {
        return mAllCards;
    }


    public void insert(Card card) {
        new insertAsyncTask(mCardDao).execute(card);
    }

    private static class insertAsyncTask extends AsyncTask<Card, Void, Void> {

        private CardDao mAsyncTaskDao;

        insertAsyncTask(CardDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Card... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
