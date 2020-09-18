package com.example.jetpackstudy.vm;

import android.app.Application;

import com.example.jetpackstudy.repository.bean.Word;
import com.example.jetpackstudy.repository.WordRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * <p>@author : tangyanghai</p>
 * <p>@time : 2020/9/17</p>
 * <p>@for : </p>
 * <p></p>
 */
public class WordViewModel extends AndroidViewModel {

    private WordRepository mRepository;

    private LiveData<List<Word>> mAllWords;

    public WordViewModel(@NonNull Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }


    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    public void insert(Word word) {
        mRepository.insert(word);
    }
}
