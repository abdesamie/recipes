package com.abnamro.recipes.web;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RecipeControllerValidationsTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String MISSING_SERVINGS_SAMPLE = "{\n" +
            "  \"id\": 8,\n" +
            "  \"title\": \"GRILLED BASIL CHICKEN\",\n" +
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

    private static final String MISSING_TITLE_SAMPLE = "{\n" +
            "  \"id\": 8,\n" +
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

    private static final String MISSING_INSTRUCTIONS_SAMPLE = "{\n" +
            "  \"id\": 8,\n" +
            "  \"title\": \"GRILLED BASIL CHICKEN\",\n" +
            "  \"servings\": 10,\n" +
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
    void addNewRecipeWithMissingServings() throws Exception {
        mockMvc.perform(post("/api/v1/recipes").content(MISSING_SERVINGS_SAMPLE).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.servings", Is.is("must not be null")));
    }

    @Test
    void addNewRecipeWithMissingTitle() throws Exception {
        mockMvc.perform(post("/api/v1/recipes").content(MISSING_TITLE_SAMPLE).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title", Is.is("must not be empty")));
    }

    @Test
    void addNewRecipeWithMissingInstructions() throws Exception {
        mockMvc.perform(post("/api/v1/recipes").content(MISSING_INSTRUCTIONS_SAMPLE).contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.instructions", Is.is("must not be blank")));
    }

    @Test
    void getRecipesUsingFilterInvalidExpression() throws Exception {
        mockMvc.perform(get("/api/v1/recipes/search?filter=ingredientNameflour"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void getRecipesUsingFilterInvalidKey() throws Exception {
        mockMvc.perform(get("/api/v1/recipes/search?filter=wrongKey:flour"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }


}
