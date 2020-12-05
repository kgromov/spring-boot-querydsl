package com.spring.templates.repositories.custom;

import com.spring.templates.domain.dtos.RecipeCookTime;
import com.spring.templates.domain.dtos.RecipeNotes;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RecipeDtoRepositoryImpl extends QueryDslRepository implements RecipeDtoRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<RecipeCookTime> getRecipesCookTime() {

        return null;
    }

    @Override
    public List<RecipeNotes> getRecipesWithNote() {
        return null;
    }
}
