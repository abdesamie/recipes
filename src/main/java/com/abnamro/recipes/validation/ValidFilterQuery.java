package com.abnamro.recipes.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FilterQueryValidation.class)
@Target( {ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidFilterQuery {
    String message() default "Invalid filter query";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}