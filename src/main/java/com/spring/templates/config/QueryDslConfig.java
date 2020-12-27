package com.spring.templates.config;

import com.querydsl.jpa.hibernate.HibernateQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.hibernate.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class QueryDslConfig {
    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }

    @Bean
    @Transactional
    // if only transaction EntityManager is configured - a bit strange; will try mysql + DbConfig
    public HibernateQueryFactory hibernateQuery() {
        Session session = entityManager.unwrap(Session.class);
        return new HibernateQueryFactory(session);
    }
}
