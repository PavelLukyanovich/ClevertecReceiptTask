package com.clevertec.receipt.services.products;

import com.clevertec.receipt.models.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAllProductsByIds(List<Long> productIds);
}
