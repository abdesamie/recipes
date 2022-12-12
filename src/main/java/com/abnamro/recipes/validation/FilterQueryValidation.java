package com.abnamro.recipes.validation;

import com.google.common.collect.Sets;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FilterQueryValidation   implements
        ConstraintValidator<ValidFilterQuery, String> {

    private static final String FILTER_PATTERN = "(\\w+?)(:|<|>|!=)(\\w+?),";
    private static final Set<String> validKeys = Sets.newHashSet(Arrays.asList("searchId", "title", "servings",
            "instructions", "categoryName", "ingredientName", "quantity", "unit"));

    @Override
    public boolean isValid(String search, ConstraintValidatorContext constraintValidatorContext) {
        if( Pattern.matches("("+ FILTER_PATTERN+")+", search + ",")){
            Pattern pattern = Pattern.compile(FILTER_PATTERN);
            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                if(!validKeys.contains( matcher.group(1)))
                    return false;
            }
            return true;
        }
        return false;
    }
}
