package com.aros.naufal.qrreader.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.aros.naufal.qrreader.data.CardRepository;
import com.aros.naufal.qrreader.data.database.entities.Card;

import java.util.List;

public class CardViewModel extends AndroidViewModel {

    private CardRepository mRepository;
    private LiveData<List<Card>> mAllCards;

    public CardViewModel(Application application) {
        super(application);
        mRepository = new CardRepository(application);
        mAllCards = mRepository.getAllCards();
    }

    public LiveData<List<Card>> getAllCards() {
        return mAllCards;
    }

    public void instert(Card card) {
        mRepository.insert(card);
    }
}
