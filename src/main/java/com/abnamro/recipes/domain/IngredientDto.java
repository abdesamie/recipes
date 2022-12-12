package com.abnamro.recipes.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class IngredientDto {
    private Long id;
    private String name;
    private Integer quantity;
    private String unit;
}
