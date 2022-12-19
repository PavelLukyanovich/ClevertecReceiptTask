package com.clevertec.receipt.services.calculations;

import com.clevertec.receipt.models.dto.BucketDto;
import com.clevertec.receipt.models.dto.ProductDto;
import com.clevertec.receipt.utils.MathOperations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class QuantityCalculationProductService implements CalculationProductService {

    @Value("${calculation.service.quantity.default-discount}")
    private Integer defaultDiscount;

    @Value("${calculation.service.quantity.default-quantity}")
    private Integer defaultQuantity;

    @Override
    public BucketDto calculate(BucketDto bucketDto) {

        List<ProductDto> rawProductDtos = bucketDto.getProductDtos();

        long quantityOfProductsDiscount = rawProductDtos.stream().filter(ProductDto::isHasDiscount).count();
//        if (quantityOfProductsDiscount >= defaultQuantity) {
//            List<ProductDto> productDtos = rawProductDtos.stream()
//                    .map(this::calculateDiscounts).toList();
//            bucketDto.setProductDtos(productDtos);
//        }else {
//
//        }

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

    private ProductDto calculateDiscounts(ProductDto productDto) {

        Double price = productDto.getPrice();
        Double discountedPrice = MathOperations.subPercentageFromNumber(price.floatValue(), defaultDiscount);
        productDto.setDiscountedPrice(discountedPrice);
        Integer quantity = productDto.getQuantity();
        Double totalPrice = discountedPrice > 0 ? discountedPrice * quantity : price * quantity;
        productDto.setTotalPrice(MathOperations.defaultRound(totalPrice));
        return productDto;
    }

}
