package com.clevertec.receipt.services.calculations;

import com.clevertec.receipt.models.dto.BucketDto;
import com.clevertec.receipt.models.dto.ProductDto;
import com.clevertec.receipt.models.entities.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
class CardCalculationProductServiceTest {
    private CardCalculationProductService service;

    @BeforeEach
    public void init() {
        service = new CardCalculationProductService();
    }

    @Test
    public void calculate_whenBucketDtoIsNull_shouldReturnNull() {

        BucketDto bucketDto = service.calculate(null);
        assertNull(bucketDto);
    }

    @Test
    public void calculate_whenProductDtosIsNull_shouldReturnNull() {

        BucketDto bucketDto = BucketDto.builder().build();
        assertNull(service.calculate(bucketDto));
    }

    @Test
    public void calculate_whenProductDtosIsEmpty_shouldReturnNull() {

        BucketDto bucketDto = BucketDto.builder()
                .productDtos(Collections.emptyList())
                .build();
        assertNull(service.calculate(bucketDto));

    }

    @Test
    public void calculate_whenProductDtosPriceNotExist_shouldReturnZero() {

        ProductDto productDto = ProductDto.builder().build();
        List<ProductDto> productDtos = List.of(productDto);

        BucketDto bucketDto = BucketDto.builder().productDtos(productDtos).build();
        BucketDto actualBucketDto = service.calculate(bucketDto);

        assertEquals(0, actualBucketDto.getReceivedSum());
    }

    @Test
    public void calculate_whenCardHasNoDiscount_shouldReturnZero() {

        ProductDto productDto = ProductDto.builder().build();
        List<ProductDto> productDtos = List.of(productDto);

        BucketDto bucketDto = BucketDto.builder()
                .productDtos(productDtos)
                .card(new Card())
                .build();
        BucketDto actualBucketDto = service.calculate(bucketDto);

        assertEquals(0, actualBucketDto.getReceivedSum());
    }

    @Test
    public void calculate_whenCardHasZeroDiscount_shouldReturnZero() {

        ProductDto productDto = ProductDto.builder().build();
        List<ProductDto> productDtos = List.of(productDto);

        BucketDto bucketDto = BucketDto.builder()
                .productDtos(productDtos)
                .card(new Card(1L, "123", 0))
                .build();
        BucketDto actualBucketDto = service.calculate(bucketDto);

        assertEquals(0, actualBucketDto.getReceivedSum());
    }

    @Test
    public void calculate_whenCardHasDiscount_shouldReturnCorrectDiscountedPrice() {

        ProductDto productDto = ProductDto.builder()
                .totalPrice(100.0)
                .hasDiscount(true)
                .build();
        ProductDto productDto2 = ProductDto.builder()
                .totalPrice(50.0)
                .hasDiscount(true)
                .build();
        List<ProductDto> productDtos = List.of(productDto, productDto2);

        BucketDto bucketDto = BucketDto.builder()
                .productDtos(productDtos)
                .card(new Card(1L, "123", 10))
                .build();
        BucketDto actualBucketDto = service.calculate(bucketDto);

        assertEquals(135.0, actualBucketDto.getReceivedSum());
    }


}