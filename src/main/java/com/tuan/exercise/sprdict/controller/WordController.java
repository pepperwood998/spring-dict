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
import com.tuan.exercise.sprdict.util.CommonUtil;

@Controller
public class WordController {

    @Autowired
    private DictionaryDao dictionaryDao;

    @GetMapping(value = { "/", "/search" })
    public String search(
            @RequestParam(name = "search-word", defaultValue = "") String searchWord,
            @RequestParam(name = "trans-type", defaultValue = "0") int transType,
            @RequestParam(name = "page", defaultValue = "1") int page,
            Model model) {

        int wordCount = 4;
        int totalPageCount = CommonUtil.getTotalPageCount(dictionaryDao.getWordCount(searchWord, transType), wordCount);
        if (page > totalPageCount) {
            page = totalPageCount;
        } else if (page < 1) {
            page = 1;
        }

        int rowStart = (page - 1) * wordCount + 1;
        model.addAttribute("words", dictionaryDao.getWordsRelative(searchWord, transType, rowStart, wordCount));

        int pageCount = 3;
        if (pageCount > totalPageCount) {
            pageCount = totalPageCount;
        }
        int pageStart = CommonUtil.getPageStart(page, pageCount, totalPageCount);
        int pageEnd = CommonUtil.getPageEnd(pageStart, pageCount);

        model.addAttribute("pageStart", pageStart);
        model.addAttribute("pageEnd", pageEnd);

        model.addAttribute("searchWord", searchWord);
        model.addAttribute("transTypes", dictionaryDao.getTransTypes());
        model.addAttribute("curTransType", transType);
        model.addAttribute("curPage", page);
        return Page.INDEX;
    }

    @GetMapping(value = { "/edit", "/add" })
    public String getEditForm(
            @RequestParam(name = "word-id", defaultValue = "-1") int wordId,
            Model model) {

        Word word;
        if (wordId >= 0) {
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
    public String doDeleteWord(@RequestParam("word-id") int wordId) {
        dictionaryDao.deleteWord(wordId);

        return Page.Direct.getRedirect("/search", null);
    }

    @PostMapping(value = { "/edit", "/add" })
    public String doUpdateWord(@ModelAttribute("word") Word savedWord) {

        dictionaryDao.saveWord(savedWord);

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("search-word", savedWord.getKey());
        paramMap.put("trans-type", savedWord.getType());

        return Page.Direct.getRedirect("/search", paramMap);
    }
}
