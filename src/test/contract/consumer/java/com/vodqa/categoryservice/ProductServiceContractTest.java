package com.vodqa.categoryservice;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonArray;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import com.vodqa.categoryservice.models.Product;
import com.vodqa.categoryservice.services.ProductServiceGateway;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProductServiceContractTest {
    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("product-service", "localhost", 8080, this);

    @Pact(provider="product-service", consumer="category-service")
    public RequestResponsePact createPactForProductList(PactDslWithProvider builder) {
        DslPart body = new PactDslJsonArray()
                .object()
                    .stringValue("id", "prod1234")
                    .stringType("name", "Table")
                    .decimalType("price", 240.0)
                    .closeObject()
                .object()
                .stringValue("id", "prod4567")
                .stringType("name", "Chair")
                .decimalType("price", 180.0)
                .closeObject();

        return builder
                .given("HasListOfProductDetails")
                .uponReceiving("A request is made with list of product ids")
                .path("/products")
                .query("productIds=prod1234&productIds=prod4567")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(body)
                .toPact();
    }

    @Pact(provider="product-service", consumer="category-service")
    public RequestResponsePact createPactForProduct(PactDslWithProvider builder) {
        DslPart body = new PactDslJsonBody();

        return builder
                .given("add a state name")
                .uponReceiving("A request is made with product id")
                .path("add your path")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(body)
                .toPact();
    }

    @Test
    @PactVerification(value = "product-service", fragment = "createPactForProductList")
    public void shouldReceiveValidProductListResponse() throws URISyntaxException, IOException {
        ProductServiceGateway gateway = new ProductServiceGateway();
        List<Product> products = gateway.getProducts(Arrays.asList("prod1234","prod4567"));
        Product product1 = products.get(0);
        assertEquals("prod1234",product1.getId());
        assertEquals("Table",product1.getName());
        assertEquals(240.0,product1.getPrice(), 0);

        Product product2 = products.get(1);
        assertEquals("prod4567",product2.getId());
        assertEquals("Chair",product2.getName());
        assertEquals(180.0,product2.getPrice(), 0);
    }

    @Test
    @PactVerification(value="product-service", fragment = "createPactForProduct")
    public void shouldReceiveValidProductDetails() {
        //add your code here
    }
}
