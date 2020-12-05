package com.spring.templates.repositories.custom;


import com.spring.templates.domain.dtos.CategoryRecipes;

import java.util.List;

public interface CategoryDtoRepository {

    List<CategoryRecipes> getAllRecipesByCategory();

    List<CategoryRecipes> getAllRecipesByCategoryRecordMapper();

    List<CategoryRecipes> getAllRecipesByCategoryFetchTo();
}
