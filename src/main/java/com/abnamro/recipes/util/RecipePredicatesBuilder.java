package com.abnamro.recipes.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

public final class RecipePredicatesBuilder {
    private final List<SearchCriteria> params;

    public RecipePredicatesBuilder() {
        params = new ArrayList<>();
    }

    public RecipePredicatesBuilder with(final String key, final String operation, final Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public BooleanExpression build() {
        if (params.isEmpty()) {
            return null;
        }
        
        final List<BooleanExpression> predicates = params.stream().map(param -> {
            RecipePredicate predicate = new RecipePredicate(param);
            return predicate.getPredicate();
        }).filter(Objects::nonNull).collect(Collectors.toList());
        
        BooleanExpression result = Expressions.asBoolean(true).isTrue();
        for (BooleanExpression predicate : predicates) {
            result = result.and(predicate);
        }
        
        return result;
    }
}