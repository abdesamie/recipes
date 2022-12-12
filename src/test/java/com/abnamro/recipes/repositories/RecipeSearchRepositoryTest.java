package com.abnamro.recipes.repositories;

import com.abnamro.recipes.entities.RecipeSearch;
import com.abnamro.recipes.util.RecipePredicatesBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
class RecipeSearchRepositoryTest {

    @Autowired
    private RecipeSearchRepository recipeSearchRepository;

    @Test
    void shouldRepositoryFindByExpression() {
        RecipePredicatesBuilder builder = new RecipePredicatesBuilder();
        builder.with("servings",":", 8);
        Iterable<RecipeSearch> recipes = recipeSearchRepository.findAll(builder.build());
        assertEquals(2, recipes.iterator().next().getSearchId());
    }

    @Test
    void shouldRepositoryFindByCompositeExpression() {
        RecipePredicatesBuilder builder = new RecipePredicatesBuilder();
        builder.with("servings",">", 1);
        builder.with("ingredientName", ":", "Flour");
        Iterable<RecipeSearch> recipes = recipeSearchRepository.findAll(builder.build());
        assertEquals(1, recipes.iterator().next().getSearchId());
    }
}