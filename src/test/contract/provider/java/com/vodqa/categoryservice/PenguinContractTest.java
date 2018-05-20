package com.vodqa.categoryservice;

import au.com.dius.pact.provider.junit.Consumer;
import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.target.MockMvcTarget;
import com.vodqa.categoryservice.controllers.CategoryController;
import com.vodqa.categoryservice.models.Category;
import com.vodqa.categoryservice.models.Product;
import com.vodqa.categoryservice.services.CategoryService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(PactRunner.class)
@Provider("category-service")
@Consumer("dummy-consumer")
@PactFolder(value = "./src/test/contract/provider/resources/pacts")
public class PenguinContractTest {

    @TestTarget
    public MockMvcTarget target = new MockMvcTarget();

    @Mock
    private CategoryService categoryService;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        CategoryController categoryController= new CategoryController(categoryService);
        target.setControllers(categoryController);
    }

    @State("HasCategoryDetails")
    public void shouldReturnListOfProductDetails(){
        List<Product> products = new ArrayList<>();
        products.add(new Product("123", "Pen", 234));
        products.add(new Product("456", "Pencil", 876));
        Category category = new Category("cat1234", products);
        when(categoryService.getProductsById("cat1234")).thenReturn(category);
    }
}
