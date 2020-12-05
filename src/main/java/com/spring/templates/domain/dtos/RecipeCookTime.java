package com.spring.templates.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeCookTime {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
}
