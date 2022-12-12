package com.abnamro.recipes.repositories;



import com.abnamro.recipes.entities.QRecipeSearch;
import com.abnamro.recipes.entities.RecipeSearch;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;

public interface RecipeSearchRepository extends JpaRepository<RecipeSearch,Long>, QuerydslPredicateExecutor<RecipeSearch>,
        QuerydslBinderCustomizer<QRecipeSearch> {

    @Override
    default void customize( QuerydslBindings bindings, QRecipeSearch root) {
        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }
}
