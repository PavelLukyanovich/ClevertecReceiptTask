package com.clevertec.receipt.services.products;

import com.clevertec.receipt.models.entities.Product;
import com.clevertec.receipt.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductServiceImpl productService;

    private static final List<Long> productIds = List.of(1L, 2L, 3L);

    @Test
    public void shouldReturnProductsByIds() {

        when(productRepository.findAllById(List.of(1L, 2L, 3L))).thenReturn(getProducts());

        List<Product> fetchProducts = productService.findAllProductsByIds(productIds);

        assertNotNull("Not null: ",fetchProducts);
        assertEquals("Products Ids: ", getProducts(), fetchProducts);
        assertEquals("Product lists equals: ",getProducts(), fetchProducts );
    }

    private static List<Product> getProducts() {

        Product milk = new Product(1L, "Milk", 12.2, true);
        Product bread = new Product(2L, "Bread", 8.2, false);
        Product butter = new Product(3L, "Butter", 15.2, true);

        return List.of(milk, bread, butter);

    }
}