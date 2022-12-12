package com.abnamro.recipes.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;


/**
 * @RestResponseEntityExceptionHandler is a central handler that intercept the exceptions risen due to input validation
 * failure
 */
@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public void handleValidationExceptions(ConstraintViolationException ex) {
        log.error(ex.getMessage());
    }


}