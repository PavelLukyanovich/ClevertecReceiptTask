package com.clevertec.receipt.models.realization;

import com.clevertec.receipt.models.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRealization {
    Product milk = new Product();
    Product water = new Product();
    Product bread = new Product();
    Product beer = new Product();
    Product butter = new Product();
    Product meat = new Product();
    Product fish = new Product();
    Product cookies = new Product();


    private List<Product> productList = new ArrayList<>();

}
