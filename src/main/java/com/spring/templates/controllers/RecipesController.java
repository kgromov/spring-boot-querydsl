package com.spring.templates.controllers;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.templates.domain.Difficulty;
import com.spring.templates.domain.QCategory;
import com.spring.templates.domain.QRecipe;
import com.spring.templates.domain.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("recipes")
@RequiredArgsConstructor
public class RecipesController {
    private final JPAQueryFactory queryFactory;

    @GetMapping
    public List<Recipe> getRecipes() {
        return queryFactory.selectFrom(QRecipe.recipe).fetch();
    }

//    @RequestMapping("/recipe/{id}")
    @Transactional
    public Recipe updateRecipe(@PathVariable("id") Long recipeId,
                               @RequestParam(value = "difficulty", required = false) Difficulty difficulty,
                               @RequestParam(value = "description", required = false) String categoryDescription) {
        QRecipe recipe = QRecipe.recipe;
        QCategory category = QCategory.category;

        Recipe recipe1 = queryFactory.selectFrom(recipe).where(recipe.id.eq(recipeId)).fetchOne();
        if (recipe1 != null) {
            if (difficulty != null) {
                recipe1.setDifficulty(difficulty);
            }
            if (categoryDescription != null) {
                Optional.ofNullable(queryFactory.selectFrom(category)
                        .where(category.description.likeIgnoreCase(categoryDescription))
                        .fetchOne())
                        .ifPresent(c -> recipe1.getCategories().add(c));
            }
        }
        return recipe1;
    }

    @RequestMapping("/recipe/{id}")
    @Transactional
    public void updateRecipeWithQueryDsl(@PathVariable("id") Long recipeId,
                                         @RequestParam(value = "difficulty", required = false) Difficulty difficulty) {
        QRecipe recipe = QRecipe.recipe;

        if (difficulty != null) {
            queryFactory.update(recipe)
                    .set(recipe.difficulty, difficulty)
                    .where(recipe.id.eq(recipeId))
            .execute();

        }
    }


}
