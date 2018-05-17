package com.vodqa.categoryservice.services;

import com.vodqa.categoryservice.models.Category;
import com.vodqa.categoryservice.models.Product;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class CategoryService {

    private RestTemplate restTemplate;

    public CategoryService() {
        this.restTemplate = new RestTemplate();
    }

    public Category getProductsById(String categoryId) {
        final String uri = "http://localhost:8080/products?productIds=prod123,prod456,prod789";
        ResponseEntity<List<Product>> rateResponse =
                restTemplate.exchange(uri,
                        HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<Product>>() {
                        });
        List<Product> products = rateResponse.getBody();
        return new Category(categoryId, products);
    }
}
