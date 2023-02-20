package com.clevertec.receipt.utils;

import com.clevertec.receipt.models.dto.ProductDto;
import com.clevertec.receipt.models.entities.Product;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(MockitoJUnitRunner.class)
class ProductConverterTest {

    @Mock
    Product product;

    @Test
    public void convert_WhenProductIsNull_ShouldReturnNull() {

        ProductDto actualProductDto = ProductConverter.convert(null);
        assertNull(actualProductDto);
    }
}