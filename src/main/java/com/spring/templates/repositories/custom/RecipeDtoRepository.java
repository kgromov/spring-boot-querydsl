package com.spring.templates.repositories.custom;

import com.spring.templates.domain.dtos.RecipeCookTime;
import com.spring.templates.domain.dtos.RecipeNotes;

import java.util.List;

public interface RecipeDtoRepository {

    List<RecipeCookTime> getRecipesCookTime();

    List<RecipeNotes> getRecipesWithNote();
}
