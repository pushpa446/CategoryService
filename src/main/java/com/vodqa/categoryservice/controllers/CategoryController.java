package com.vodqa.categoryservice.controllers;

import com.vodqa.categoryservice.models.Category;
import com.vodqa.categoryservice.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/category/{categoryId}")
    public Category getProductDetails(@PathVariable String categoryId) {
        return categoryService.getProductsById(categoryId);
    }
}
