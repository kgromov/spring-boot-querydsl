package com.spring.templates.repositories.custom;

import com.querydsl.jpa.hibernate.HibernateQueryFactory;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class HibernateQueryDslRepository {
    @PersistenceContext
    protected EntityManager entityManager;

    public HibernateQueryFactory getHibernateQueryFactory() {
        Session session = entityManager.unwrap(Session.class);
        return new HibernateQueryFactory(session);
    }

}
