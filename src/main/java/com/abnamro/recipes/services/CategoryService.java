package com.abnamro.recipes.services;

import com.abnamro.recipes.entities.Category;

import java.util.Optional;

public interface CategoryService {
    Optional<Category> findCategoryById(Long id);
}
