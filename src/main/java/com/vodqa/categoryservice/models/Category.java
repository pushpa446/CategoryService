package com.vodqa.categoryservice.models;

import java.util.List;

public class Category {
    private String id;
    private List<Product> products;

    public Category(String id, List<Product> products) {
        this.id = id;
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public List<Product> getProducts() {
        return products;
    }
}
