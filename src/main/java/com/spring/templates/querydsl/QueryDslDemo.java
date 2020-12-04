package com.spring.templates.querydsl;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.templates.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.Query;
import java.util.List;

@Component
public class QueryDslDemo {
    @Autowired
    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {
        QCategory category = QCategory.category;
        JPAQuery<Category> query = queryFactory.selectFrom(category);
        String s = query.toString();
        List<Category> categories = query.fetch();

        QRecipe recipe = QRecipe.recipe;
        List<Recipe> persons = queryFactory.selectFrom(recipe).where(recipe.description.like("American")).fetch();

        Query query1 = queryFactory.selectFrom(QNotes.notes).createQuery();
    }
}
