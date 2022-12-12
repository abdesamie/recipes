package com.abnamro.recipes.repositories;

import com.abnamro.recipes.entities.QRecipe;
import com.abnamro.recipes.entities.Recipe;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe,Long>, QuerydslPredicateExecutor<Recipe>,
        QuerydslBinderCustomizer<QRecipe> {
    @Override
    default void customize(
            QuerydslBindings bindings, QRecipe root) {
        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }

    @Query("SELECT recipe FROM Recipe recipe JOIN recipe.categories category WHERE category.name=:type " )
    List<Recipe> findRecipeByCategoryName(@Param("type") final String type);

    List<Recipe> findByInstructionsContaining(String instruction);
}
