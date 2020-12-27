package com.spring.templates.querydsl;

import com.spring.templates.domain.Recipe;
import com.spring.templates.domain.dtos.CategoryRecipes;
import com.spring.templates.repositories.custom.CategoryDtoRepository;
import com.spring.templates.repositories.custom.HibernateQueryDslRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class QueryDslService {
    private final CategoryDtoRepository categoryDtoRepository;
    private final HibernateQueryDslRepositoryImpl repository;

    @PostConstruct
    public void test() {
        log.info("############### Running QueryDSL ################");
//        List<Recipe> allRecipes = repository.getAllRecipes();
//        List<CategoryRecipes> allRecipesByCategory = categoryDtoRepository.getAllRecipesByCategory();
        CategoryRecipes recipes1 = categoryDtoRepository.getAllRecipesByCategoryRecordMapper().get(0);
        CategoryRecipes recipes2 = categoryDtoRepository.getAllRecipesByCategoryFetchTo().get(0);
//        log.info("First categoryRecipe = {}", allRecipesByCategory.get(0));
//        log.info("recipes clazz = {}", allRecipesByCategory.get(0).getRecipes().getClass());
//        Long countOfRecipe = allRecipesByCategory.get(0).getRecipes();
        log.info("#############################################");
    }
}
