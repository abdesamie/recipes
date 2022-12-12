package com.abnamro.recipes.services;

import com.abnamro.recipes.domain.RecipeDto;

import java.util.List;

public interface RecipeService {
    List<RecipeDto> findSearchedRecipesByFilter(String search);

    List<RecipeDto> findAllRecipes();

    RecipeDto saveRecipe(RecipeDto recipe);
}
