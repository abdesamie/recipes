package com.abnamro.recipes.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="recipeSequence")
    @SequenceGenerator(name="recipeSequence", sequenceName="RECIPE_SEQ",allocationSize=1)
    private Long id;
    private String title;
    private Integer servings;
    @OneToMany(mappedBy = "recipe",cascade = CascadeType.ALL)
    private List<Ingredient> ingredients;
    private String instructions;
    @ManyToMany(mappedBy = "recipes", cascade = CascadeType.ALL)
    private Set<Category> categories;
}
