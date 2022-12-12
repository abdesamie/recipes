package com.abnamro.recipes.mappers;

import com.abnamro.recipes.domain.RecipeDto;
import com.abnamro.recipes.entities.Recipe;
import com.abnamro.recipes.entities.RecipeSearch;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Recipe Mapper is a transformer that create POJO objects from Jpa Entities and vice versa, their goal is to decouple
 * the code in service layer from the data access layer
 */
@Component
public class RecipeMapper {

    public RecipeDto transformToRecipeDto(final Recipe recipe){
        return RecipeDto.builder().id(recipe.getId()).instructions(recipe.getInstructions()).title(recipe.getTitle())
                .servings(recipe.getServings())
                .build();
    }

    public List<RecipeDto> transformToRecipeDto(final Iterable<Recipe> recipes){
        List<RecipeDto> recipeDtoList = new ArrayList<>();
        for (Recipe recipe :
                recipes) {
            recipeDtoList.add(transformToRecipeDto(recipe));
        }
        return recipeDtoList;
    }

    public Recipe transformToRecipe(final RecipeDto recipeDto) {
        return Recipe.builder().instructions(recipeDto.getInstructions()).title(recipeDto.getTitle())
                .servings(recipeDto.getServings()).build();
    }

    public List<RecipeDto> transformRecipeSearchToRecipeDto(final Iterable<RecipeSearch> recipes) {
        List<RecipeDto> recipeDtoList = new ArrayList<>();
        for (RecipeSearch recipe : recipes) {
            recipeDtoList.add(
                RecipeDto.builder().id(recipe.getSearchId()).servings(recipe.getServings()).title(recipe.getTitle())
                        .instructions(recipe.getInstructions())
                        .build()
            );
        }
        return recipeDtoList;
    }
}
