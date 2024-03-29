package com.clevertec.receipt.services.calculations;

import com.clevertec.receipt.models.dto.BucketDto;
import com.clevertec.receipt.models.dto.ProductDto;
import com.clevertec.receipt.utils.MathOperations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class QuantityCalculationProductService implements CalculationProductService {

    @Value("${calculation.service.quantity.default-discount}")
    private Integer defaultDiscount;

    @Value("${calculation.service.quantity.default-quantity}")
    private Integer defaultQuantity;

    @Override
    public BucketDto calculate(BucketDto bucketDto) {

        if (Objects.isNull(bucketDto) || Objects.isNull(bucketDto.getProductDtos())
        || bucketDto.getProductDtos().size() == 0.0) {
            return null;
        }

        List<ProductDto> rawProductDtos = bucketDto.getProductDtos();

        long quantityOfProductsDiscount = rawProductDtos.stream()
                .filter(ProductDto::isHasDiscount)
                .count();

        rawProductDtos.forEach(productDto -> {
            Double discountedPrice = 0.0;
            Double price = productDto.getPrice();
            if (quantityOfProductsDiscount >= defaultQuantity) {
                discountedPrice = MathOperations.subPercentageFromNumber(price.floatValue(), defaultDiscount);
                productDto.setDiscountedPrice(discountedPrice);

            }
            Integer quantity = productDto.getQuantity();
            Double totalPrice = discountedPrice > 0 ? discountedPrice * quantity : price * quantity;
            productDto.setTotalPrice(MathOperations.defaultRound(totalPrice));
        });

        return bucketDto;
    }
}
