package com.abnamro.recipes.services.impl;

import com.abnamro.recipes.domain.RecipeDto;
import com.abnamro.recipes.entities.Ingredient;
import com.abnamro.recipes.entities.Recipe;
import com.abnamro.recipes.entities.RecipeSearch;
import com.abnamro.recipes.mappers.IngredientMapper;
import com.abnamro.recipes.mappers.RecipeMapper;
import com.abnamro.recipes.repositories.RecipeRepository;
import com.abnamro.recipes.repositories.RecipeSearchRepository;
import com.abnamro.recipes.services.RecipeService;
import com.abnamro.recipes.util.RecipePredicatesBuilder;
import com.google.common.collect.Sets;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RecipeServiceImpl implements RecipeService {


    public static final String FILTER_PATTERN = "(\\w+?)(:|<|>|!=)(\\w+?),";
    private final RecipeRepository recipeRepository;
    private final RecipeSearchRepository recipeSearchRepository;

    private final RecipeMapper recipeMapper;
    private final IngredientMapper ingredientMapper;

    @Override
    public List<RecipeDto> findSearchedRecipesByFilter(final String search) {
        BooleanExpression expression = generateExpressionFromQuery(search);
        Iterable<RecipeSearch> recipes = recipeSearchRepository.findAll(expression);
        return  recipeMapper.transformRecipeSearchToRecipeDto(Sets.newHashSet(recipes));
    }

    @Override
    public List<RecipeDto> findAllRecipes() {
        return recipeMapper.transformToRecipeDto(recipeRepository.findAll());
    }

    @Override
    public RecipeDto saveRecipe(RecipeDto recipeDto) {
        Recipe recipe = recipeMapper.transformToRecipe(recipeDto);
        List<Ingredient> ingredients = recipeDto.getIngredients().stream()
                .map(ingredientDto -> ingredientMapper.transformToIngredient(ingredientDto))
                .collect(Collectors.toList());
        recipe.setIngredients(ingredients);
         return recipeMapper.transformToRecipeDto(recipeRepository.save(recipe));
    }


    private BooleanExpression generateExpressionFromQuery(String search) {
        RecipePredicatesBuilder recipePredicatesBuilder = new RecipePredicatesBuilder();
        if (search != null) {
            Pattern pattern = Pattern.compile(FILTER_PATTERN);
            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                recipePredicatesBuilder.with(matcher.group(1), matcher.group(2), matcher.group(3));
            }
        }
        return recipePredicatesBuilder.build();
    }


}
