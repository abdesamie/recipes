package com.abnamro.recipes.domain;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Builder
@Data
public class RecipeDto {
    private Long id;
    @NotEmpty
    private String title;
    @NotNull
    private Integer servings;
    @NotBlank
    private String instructions;
    private List<Long> categories;
    private List<IngredientDto> ingredients;
}
