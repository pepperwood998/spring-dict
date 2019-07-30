package com.tuan.exercise.sprdict.dao;

import java.util.List;

import com.tuan.exercise.sprdict.entity.TransTypeDetail;
import com.tuan.exercise.sprdict.entity.Word;

public interface DictionaryDao {

    public List<Word> getWordsRelative(String relativeKey, int transType, int rowStart, int rowCount);

    public Word getWordById(int id);

    public List<TransTypeDetail> getTransTypes();

    public void saveMeanings(int wordId, String meanings);

    public void saveWord(Word word);

    public void deleteWord(int wordId);

    public Long getWordCount(String relativeKey, int transType);
}
