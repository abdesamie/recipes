package com.abnamro.recipes.web;

import com.abnamro.recipes.validation.ValidFilterQuery;
import com.abnamro.recipes.domain.RecipeDto;
import com.abnamro.recipes.services.RecipeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@AllArgsConstructor
@RestController
@Validated
@RequestMapping(path = "api/v1/recipes")
public class RecipeController {

    public final RecipeService recipeService;



    @PostMapping
    public ResponseEntity<RecipeDto> createRecipe(@Valid @RequestBody RecipeDto recipe){
        log.info("Processing the request for /api/recipes to create new recipe");
        log.debug("Calling service.saveRecipeToRepository to save recipe into DB");
        RecipeDto savedRecipe = recipeService.saveRecipe(recipe);

        log.info("Service successfully saved new recipe into DB with recipeId: "+savedRecipe.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRecipe);

    }

    @Operation(summary =  "Retrieve all recipes")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved !", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = RecipeDto.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Unexpected system exception", content = @Content)
            }
    )
    @GetMapping
    public  List<RecipeDto> getAllRecipes(){
        log.info("Retrieving all recipes  ");
        return recipeService.findAllRecipes();
    }

    @Operation(summary =  "Retrieve recipes by filter using the pattern : key operation value e.g :  ingredientName:Egg " +
            "operation can be : or > or < or even != and possible keys are : title, servings, " +
            "            instructions, categoryName, ingredientName, quantity also you can combine multiple criterion using comma " +
            "as a separator")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved !", content =
                    @Content(mediaType = "application/json", schema = @Schema(implementation = RecipeDto.class))),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Unexpected system exception", content = @Content)
            }
    )
    @GetMapping(value = "/search")
    public List<RecipeDto> findSearchedRecipesByFilter(@RequestParam(value = "filter")@ValidFilterQuery String search) {
        log.info("Search recipes using filter : {}", search);
        return recipeService.findSearchedRecipesByFilter(search);
    }





    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
