package com.clevertec.receipt.models.dto;

import com.clevertec.receipt.models.entities.Card;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BucketDto {

    private List<ProductDto> productDtos;

    private Card card;

    private Double receivedSum;

    private Double discountSum;
}
