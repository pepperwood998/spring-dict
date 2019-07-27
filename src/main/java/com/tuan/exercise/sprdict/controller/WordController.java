package com.tuan.exercise.sprdict.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tuan.exercise.sprdict.dao.DictionaryDao;
import com.tuan.exercise.sprdict.entity.TransTypeDetail;
import com.tuan.exercise.sprdict.entity.Word;

@Controller
public class WordController {

    @Autowired
    private DictionaryDao dictionaryDao;

    @GetMapping(value = { "/", "/search" })
    public String search(
            @RequestParam(name = "search-word", defaultValue = "") String searchWord,
            @RequestParam(name = "trans-type", defaultValue = "0") String transTypeStr,
            Model model) {

        int transType = 0;
        try {
            transType = Integer.valueOf(transTypeStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        List<Word> searchRes = dictionaryDao.getWordsRelative(searchWord, transType);
        List<TransTypeDetail> transTypes = dictionaryDao.getTransTypes();

        model.addAttribute("words", searchRes);
        model.addAttribute("searchWord", searchWord);
        model.addAttribute("transTypes", transTypes);
        model.addAttribute("curTransType", transType);

        return "index";
    }
    
    @GetMapping("/edit")
    public String edit(
            @RequestParam(name = "word-id") String wordIdStr,
            Model model) {
        
        int wordId = 0;
        try {
            wordId = Integer.valueOf(wordIdStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Word word = dictionaryDao.getWordById(wordId);
        List<TransTypeDetail> transTypes = dictionaryDao.getTransTypes();
        model.addAttribute("word", word);
        model.addAttribute("transTypes", transTypes);
        model.addAttribute("wordOperation", "Edit word");
        return "word-input";
    }
}
