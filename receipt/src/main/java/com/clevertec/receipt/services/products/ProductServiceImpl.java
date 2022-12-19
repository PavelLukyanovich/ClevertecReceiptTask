package com.clevertec.receipt.services.products;

import com.clevertec.receipt.models.entities.Product;
import com.clevertec.receipt.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;

    @Override
    public List<Product> findAllProductsByIds(List<Long> productIds) {

        log.info("find all products by ids {}", productIds);
        List<Product> products = productRepository.findAllById(productIds);
        log.info("Fetched products {}", products);

        return products;
    }
}
