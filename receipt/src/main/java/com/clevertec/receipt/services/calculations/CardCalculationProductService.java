package com.clevertec.receipt.services.calculations;

import com.clevertec.receipt.models.dto.BucketDto;
import com.clevertec.receipt.models.dto.ProductDto;
import com.clevertec.receipt.utils.MathOperations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class CardCalculationProductService implements CalculationProductService {

    @Override
    public BucketDto calculate(BucketDto bucketDto) {
        log.info("card calculation {}", bucketDto);
        Double rawReceiptTotalPrice = bucketDto.getProductDtos().stream()
                .map(ProductDto::getTotalPrice)
                .reduce(Double::sum)
                .get();

        if (Objects.nonNull(bucketDto.getCard())) {
            Double discountedTotalPrice = MathOperations
                    .subPercentageFromNumber(rawReceiptTotalPrice.floatValue(), bucketDto.getCard().getDiscount());
            bucketDto.setDiscountSum(rawReceiptTotalPrice - discountedTotalPrice);
            rawReceiptTotalPrice = discountedTotalPrice;
        }
        bucketDto.setReceivedSum(rawReceiptTotalPrice);

        return bucketDto;
    }

}
