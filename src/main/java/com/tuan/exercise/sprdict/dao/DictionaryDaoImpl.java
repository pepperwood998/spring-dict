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
    public List<Word> getWordsRelative(String relativeKey, int transType, int rowStart, int rowCount) {
        Session currentSession = sessionFactory.getCurrentSession();

        String queryStr = "from Word as w "
                + "where w.key like :relativekey "
                + "and w.type = :transType "
                + "order by w.key";
        Query<Word> query = currentSession.createQuery(queryStr, Word.class)
                .setParameter("relativekey", String.format("%%%s%%", relativeKey))
                .setParameter("transType", transType);

        if (rowStart > 0) {
            query.setFirstResult(rowStart - 1);
            query.setMaxResults(rowCount);
        }

        return query.getResultList();
    }

    @Override
    @Transactional
    public Word getWordById(int id) {
        Session currentSession = sessionFactory.getCurrentSession();

        String queryStr = "from Word as w "
                + "where w.id = :id";
        Query<Word> query = currentSession.createQuery(queryStr, Word.class)
                .setParameter("id", id);

        return query.getSingleResult();
    }

    @Override
    @Transactional
    public List<TransTypeDetail> getTransTypes() {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<TransTypeDetail> query = currentSession.createQuery("from TransTypeDetail", TransTypeDetail.class);

        return query.getResultList();
    }

    @Override
    @Transactional
    public void saveMeanings(int wordId, String meanings) {
        Session currentSession = sessionFactory.getCurrentSession();

        String queryStr = "update Word as w set w.meanings = :meanings "
                + "where id = :id";

        @SuppressWarnings("rawtypes")
        Query query = currentSession.createQuery(queryStr)
        .setParameter("meanings", meanings)
        .setParameter("id", wordId);

        query.executeUpdate();
    }

    @Override
    @Transactional
    public void saveWord(Word word) {
        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.save(word);
    }

    @Override
    @Transactional
    public void deleteWord(int wordId) {
        Session currentSession = sessionFactory.getCurrentSession();

        String queryStr = "delete from Word as w where w.id = :wordId";
        @SuppressWarnings("rawtypes")
        Query query = currentSession.createQuery(queryStr)
                .setParameter("wordId", wordId);

        query.executeUpdate();
    }

    @Override
    @Transactional
    public Long getWordCount(String relativeKey, int transType) {
        Session currentSession = sessionFactory.getCurrentSession();

        String queryStr = "select count(*) as row_num "
                + "from Word as w "
                + "where w.key like :relativeKey "
                + "and w.type = :transType";
        Query<Long> query = currentSession.createQuery(queryStr, Long.class)
                .setParameter("relativeKey", String.format("%%%s%%", relativeKey))
                .setParameter("transType", transType);

        return query.getSingleResult();
    }

}
