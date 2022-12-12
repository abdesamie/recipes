package com.abnamro.recipes.entities;

import lombok.Builder;

import javax.persistence.*;

@Builder
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ingredientSequence")
    @SequenceGenerator(name="ingredientSequence", sequenceName="INGREDIENT_SEQ",allocationSize=1)
    private Long id;
    private String name;
    private Integer quantity;
    private String unit;
    @ManyToOne
    private Recipe recipe;
}
