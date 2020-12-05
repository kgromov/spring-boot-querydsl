package com.spring.templates.domain.dtos;

import com.spring.templates.domain.Difficulty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecipeNotes {
    private Long id;
    private String description;
    private String recipeNotes;
    private Difficulty difficulty;

    public RecipeNotes(Long id, String description, String recipeNotes, String difficulty) {
        this.id = id;
        this.description = description;
        this.recipeNotes = recipeNotes;
        this.difficulty = Difficulty.valueOf(difficulty);
    }
}
