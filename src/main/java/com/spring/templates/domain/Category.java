package com.spring.templates.domain;

import com.spring.templates.domain.dtos.CategoryRecipes;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

import static com.spring.templates.repositories.custom.CategoryDtoRepositoryImpl.CATEGORY_RECIPES_MAPPER;

/**
 * Created by jt on 6/13/17.
 */
@Getter
@Setter
@EqualsAndHashCode(exclude = {"recipes"})
@Entity
@SqlResultSetMapping(name = CATEGORY_RECIPES_MAPPER,
        classes = @ConstructorResult(
                targetClass = CategoryRecipes.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "description", type = String.class),
                        @ColumnResult(name = "recipes", type = Long.class)
                }))
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

}
