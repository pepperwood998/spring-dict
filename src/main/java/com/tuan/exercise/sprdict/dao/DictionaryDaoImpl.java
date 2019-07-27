package com.tuan.exercise.sprdict.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.tuan.exercise.sprdict.entity.TransTypeDetail;
import com.tuan.exercise.sprdict.entity.Word;

@Repository
@Primary
public class DictionaryDaoImpl implements DictionaryDao {

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    @Transactional
    public List<Word> getWordsRelative(String relativeKey, int transType) {
        List<Word> words;

        Session currentSession = sessionFactory.getCurrentSession();

        String queryStr = "from Word as w "
                + "where w.key like :relativekey "
                + "and w.type = :transType";
        Query<Word> query = currentSession.createQuery(queryStr, Word.class)
                .setParameter("relativekey", String.format("%%%s%%", relativeKey))
                .setParameter("transType", transType);
        words = query.getResultList();

        return words;
    }

    @Override
    @Transactional
    public Word getWordById(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Word word;
        
        String queryStr = "from Word as w "
                + "where w.id = :id";
        Query<Word> query = currentSession.createQuery(queryStr, Word.class)
                .setParameter("id", id);
        word = query.getSingleResult();
        
        return word;
    }

    @Override
    @Transactional
    public List<TransTypeDetail> getTransTypes() {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<TransTypeDetail> query = currentSession.createQuery("from TransTypeDetail", TransTypeDetail.class);
        List<TransTypeDetail> typeDetails = query.getResultList();
        
        return typeDetails;
    }

}
