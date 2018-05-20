package com.vodqa.categoryservice.services;

import com.vodqa.categoryservice.models.Product;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceGateway {
    private RestTemplate restTemplate;

    public ProductServiceGateway() {
        this.restTemplate = new RestTemplate();
    }

    public List<Product> getProducts(List<String> productIds){
        String ids = productIds.stream().collect(Collectors.joining("&productIds="));
        final String uri = "http://localhost:8080/products?productIds="+ids;
        ResponseEntity<List<Product>> response = restTemplate.exchange(
                uri,
                HttpMethod.GET, HttpEntity.EMPTY,
                new ParameterizedTypeReference<List<Product>>() {
                });
        return response.getBody();
    }

    public Product getProduct(String productId){
        final String uri = "http://localhost:8080/products/"+productId;
        ResponseEntity<Product> response = restTemplate.exchange(
                uri,
                HttpMethod.GET, HttpEntity.EMPTY,
                new ParameterizedTypeReference<Product>() {
                });
        return response.getBody();
    }
}
