package com.abnamro.recipes.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Immutable
@Subselect("SELECT r.id as search_id, r.title as title, r.servings as servings, r.instructions instructions, " +
        "cat.name as category_name, ing.name as ingredient_name, ing.unit as unit, ing.quantity as quantity " +
        "FROM RECIPE r  JOIN INGREDIENT  ing  " +
        "ON  r.id=ing.recipe_id " +
        "JOIN CATEGORY_RECIPES  cat_rec " +
        "ON cat_rec.recipes_id =  r.id " +
        " JOIN CATEGORY cat " +
        "ON cat.id = cat_rec.categories_id")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecipeSearch {
    @Id
    private Long searchId;
    @Column
    private String title;
    @Column
    private Integer servings;
    @Column
    private String instructions;
    @Column
    private String categoryName;
    @Column
    private String ingredientName;
    @Column
    private Integer quantity;
    @Column
    private String unit;

}
