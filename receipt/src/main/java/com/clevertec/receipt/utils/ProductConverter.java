package com.clevertec.receipt.utils;

import com.clevertec.receipt.models.dto.ProductDto;
import com.clevertec.receipt.models.entities.Product;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductConverter {

    public static ProductDto convert(Product product) {

        return Optional.ofNullable(product)
                .map(prod -> ProductDto.builder()
                        .id(prod.getId())
                        .title(prod.getTitle())
                        .price(prod.getPrice())
                        .hasDiscount(prod.isHasDiscount())
                        .build())
                .orElse(null);
    }
}
