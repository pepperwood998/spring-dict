package com.tuan.exercise.sprdict.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class AccountDaoImpl implements AccountDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public String isAccountValid(String username, String password) {
        String role = null;

        Session currentSession = sessionFactory.getCurrentSession();

        String queryStr = "select acc.role from Account as acc "
                + "where acc.username = :username and acc.password = :password";
        Query<String> query = currentSession.createQuery(queryStr, String.class)
                .setParameter("username", username)
                .setParameter("password", password);

        List<String> rows = query.getResultList();

        if (!rows.isEmpty()) {
            role = rows.get(0);
        }

        return role;
    }

}
