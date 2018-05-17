package com.vodqa.categoryservice.services;

import com.vodqa.categoryservice.models.Category;
import com.vodqa.categoryservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CategoryService {

    private ProductServiceGateway productServiceGateway;

    @Autowired
    public CategoryService(ProductServiceGateway productService) {
        this.productServiceGateway = productService;
    }

    public Category getProductsById(String categoryId) {
        //Assume we are getting list of product ids under a category from a CategoryRepository
        List<String> productIds = new ArrayList<>(Arrays.asList("1234", "4567", "891"));

        List<Product> products = productServiceGateway.getProducts(productIds);
        return new Category(categoryId, products);
    }
}
