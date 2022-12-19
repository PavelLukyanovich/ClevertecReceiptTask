package com.clevertec.receipt.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductDto {

    private Long id;

    private String title;

    private Integer quantity;

    private Double price;

    private Double totalPrice;

    private boolean hasDiscount;

    private Double discountedPrice;
}
