package com.clevertec.receipt.services.calculations;
import com.clevertec.receipt.models.dto.BucketDto;
import com.clevertec.receipt.models.dto.ProductDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(MockitoJUnitRunner.class)
class QuantityCalculationProductServiceTest {
    @Mock
    ProductDto productDto;

    private QuantityCalculationProductService service;

    @BeforeEach
    public void init() {

        service = new QuantityCalculationProductService();

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
}