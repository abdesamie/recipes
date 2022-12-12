package com.abnamro.recipes.services.impl;

import com.abnamro.recipes.domain.RecipeDto;
import com.abnamro.recipes.entities.RecipeSearch;
import com.abnamro.recipes.mappers.IngredientMapper;
import com.abnamro.recipes.mappers.RecipeMapper;
import com.abnamro.recipes.repositories.RecipeRepository;
import com.abnamro.recipes.repositories.RecipeSearchRepository;
import com.abnamro.recipes.services.RecipeService;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


class RecipeServiceImplTest {

    @Mock
    private RecipeRepository recipeRepository;
    @Mock
    private RecipeSearchRepository recipeSearchRepository;

    private RecipeService recipeService;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository , recipeSearchRepository ,  new RecipeMapper(), new IngredientMapper());
    }

    @Test
    void findSearchedRecipesByFilter() {
        Mockito.when(recipeSearchRepository.findAll(any(BooleanExpression.class)))
                .thenReturn(Arrays.asList(RecipeSearch.builder().searchId(1l).servings(5).instructions("Instructions").build()));

        List<RecipeDto> recipes = recipeService.findSearchedRecipesByFilter("servings!=1");
        assertEquals(1,recipes.size());
        assertEquals(1l, recipes.get(0).getId());
        assertEquals("Instructions", recipes.get(0).getInstructions());
    }

}