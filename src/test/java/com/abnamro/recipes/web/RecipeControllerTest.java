package com.abnamro.recipes.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String RECIPE_SAMPLE = "{\n" +
            "  \"id\": 8,\n" +
            "  \"title\": \"GRILLED BASIL CHICKEN\",\n" +
            "  \"servings\": 10,\n" +
            "  \"instructions\": \"Place chicken breasts in a shallow dish; orange quote icon do not rinse raw poultry. Cover with marinade. Cover dish. Refrigerate about 1 hour, turning occasionally.Place chicken on an oiled grill rack over medium heat \",\n" +
            "  \"categories\":[\n" +
            "    4\n" +
            "  ],\n" +
            "  \"ingredients\": [\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"name\": \"garlic clove\",\n" +
            "      \"quantity\": 1,\n" +
            "      \"unit\": \"piece\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

    @Test
    void getRecipesWithASpecificDietary() throws Exception {

        mockMvc.perform(get("/api/v1/recipes?dietary=vegan",1))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void addNewRecipe() throws Exception {
        mockMvc.perform(post("/api/v1/recipes").content(RECIPE_SAMPLE).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }


    @Test
    void getAllRecipes() throws Exception {

        mockMvc.perform(get("/api/v1/recipes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id").value(containsInAnyOrder(1, 2)));
    }

    @Test
    void getRecipesUsingFilterExpressionForServings() throws Exception {
        mockMvc.perform(get("/api/v1/recipes/search?filter=servings>1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id").value(containsInAnyOrder(1,2)));
    }

    @Test
    void getRecipesUsingFilterExpressionInstructionContaining() throws Exception {
        mockMvc.perform(get("/api/v1/recipes/search?filter=instructions:oven"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id").value(containsInAnyOrder(2)));
    }

    @Test
    void getRecipesUsingFilterExpressionInstructionContainingAndWithoutIngredient() throws Exception {
        mockMvc.perform(get("/api/v1/recipes/search?filter=ingredientName!=salmon,instructions:oven"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id").value(containsInAnyOrder(2)));
    }

    @Test
    void getRecipesUsingFilterExpressionServingsAndHasIngredient() throws Exception {
        mockMvc.perform(get("/api/v1/recipes/search?filter=ingredientName:Tomatoes,servings>1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id").value(containsInAnyOrder(2)));
    }

    @Test
    void getRecipesUsingFilterExpressionCategoryName() throws Exception {
        mockMvc.perform(get("/api/v1/recipes/search?filter=categoryName:vegetarian"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id").value(containsInAnyOrder(1, 2)));
    }
    @Test
    void getRecipesUsingFilterExpressionIngredientsContaining() throws Exception {
        mockMvc.perform(get("/api/v1/recipes/search?filter=ingredientName:flour"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id").value(containsInAnyOrder(1)));
    }

}