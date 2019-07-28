package com.tuan.exercise.sprdict.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tuan.exercise.sprdict.constant.Page;
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

        return Page.INDEX;
    }

    @GetMapping(value = { "/edit", "/add" })
    public String getEditForm(
            @RequestParam(name = "word-id", required = false) String wordIdStr,
            Model model) {

        Word word;
        if (wordIdStr != null) {
            int wordId = 0;
            try {
                wordId = Integer.valueOf(wordIdStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }

            word = dictionaryDao.getWordById(wordId);
        } else {
            word = new Word();
        }
        model.addAttribute("word", word);
        List<TransTypeDetail> transTypes = dictionaryDao.getTransTypes();
        model.addAttribute("transTypes", transTypes);

        return Page.WORD_INPUT;
    }
    
    @GetMapping("/delete")
    public String doDeleteWord(@RequestParam("word-id") String wordIdStr) {
        
        int wordId = 0;
        try {
            wordId = Integer.valueOf(wordIdStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        
        dictionaryDao.deleteWord(wordId);
        
        return Page.Direct.getRedirect("/search", null);
    }
    
    @PostMapping(value = { "/edit", "/add" })
    public String doUpdateWord(@ModelAttribute("word") Word editedWord) {

        dictionaryDao.saveWord(editedWord);

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("search-word", editedWord.getKey());
        paramMap.put("trans-type", editedWord.getType());

        return Page.Direct.getRedirect("/search", paramMap);
    }
}
