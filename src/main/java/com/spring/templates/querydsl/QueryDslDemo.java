package com.spring.templates.querydsl;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.templates.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.Query;
import java.util.List;

@Slf4j
@Component
public class QueryDslDemo {
    @Autowired
    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {
        QRecipe recipe = QRecipe.recipe;
        QNotes notes = QNotes.notes;
        QCategory category = QCategory.category;
        // easy integrates no mapping required
        List<Recipe> recipes = queryFactory.selectFrom(recipe).fetch();
        List<Recipe> persons = queryFactory.selectFrom(recipe).where(recipe.description.like("American")).fetch();

        JPAQuery<Category> query = queryFactory.selectFrom(category);
        String sql = query.toString();
        List<Category> categories = query.fetch();
        Category category1 = new Category();
//        queryFactory.query().contains(); read docs

        // get javax.persistence.Query directly
        Query query1 = queryFactory.selectFrom(QNotes.notes).createQuery();

        // not type safety
        JPAQuery<Tuple> groupBy = queryFactory.select(recipe.id, recipe.description, recipe.count())
                .from(recipe)
                .join(notes)
                .on(recipe.notes.id.eq(notes.id))
                .groupBy(notes.id);
        log.info("Sql query = {}", groupBy.toString());
    }
}
