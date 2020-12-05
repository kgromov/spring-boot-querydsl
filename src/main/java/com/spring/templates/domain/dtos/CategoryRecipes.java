package com.spring.templates.domain.dtos;

import com.querydsl.core.annotations.QueryProjection;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryRecipes {
    private Long id;
    private String description;
    private Long recipes;

    @QueryProjection
    public CategoryRecipes(Long id, String description, Long recipes) {
        this.id = id;
        this.description = description;
        this.recipes = recipes;
    }
}
