package com.spring.templates.repositories.custom;

import com.querydsl.jpa.hibernate.HibernateQueryFactory;
import com.spring.templates.domain.QRecipe;
import com.spring.templates.domain.Recipe;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class HibernateQueryDslRepositoryImpl extends HibernateQueryDslRepository {

    @Transactional
    public List<Recipe> getAllRecipes() {
        QRecipe recipe = QRecipe.recipe;
        HibernateQueryFactory queryFactory = getHibernateQueryFactory();
        return queryFactory.selectFrom(recipe).fetch();
    }
}
