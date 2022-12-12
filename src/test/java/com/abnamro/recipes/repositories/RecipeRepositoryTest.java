package com.abnamro.recipes.repositories;

import com.abnamro.recipes.entities.Recipe;
import com.abnamro.recipes.util.RecipePredicatesBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class RecipeRepositoryTest {

    @Autowired
    private RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() {
    }


    @Test
    void shouldRepositoryFindRecipeByCategoryName() {
        List<Recipe> recipes = recipeRepository.findRecipeByCategoryName("vegetarian");
        assertEquals(2, recipes.size());
    }

    @Test
    void shouldRepositoryFindByInstructionsContaining() {
        List<Recipe> recipes = recipeRepository.findByInstructionsContaining("oven");
        assertEquals(1, recipes.size());
    }

}