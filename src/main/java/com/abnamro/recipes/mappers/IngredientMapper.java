package com.abnamro.recipes.mappers;

import com.abnamro.recipes.domain.IngredientDto;
import com.abnamro.recipes.entities.Ingredient;
import org.springframework.stereotype.Component;

@Component
public class IngredientMapper {
    public Ingredient transformToIngredient(IngredientDto ingredientDto) {
        return Ingredient.builder().unit(ingredientDto.getUnit()).name(ingredientDto.getName())
                .quantity(ingredientDto.getQuantity()).build();
    }
}
