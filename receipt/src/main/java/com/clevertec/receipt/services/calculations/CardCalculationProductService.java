package com.clevertec.receipt.services.calculations;

import com.clevertec.receipt.models.dto.BucketDto;
import com.clevertec.receipt.models.dto.ProductDto;
import com.clevertec.receipt.models.entities.Card;
import com.clevertec.receipt.utils.MathOperations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class CardCalculationProductService implements CalculationProductService {

    @Override
    public BucketDto calculate(BucketDto bucketDto) {


        if (Objects.isNull(bucketDto) || Objects.isNull(bucketDto.getProductDtos())
                || bucketDto.getProductDtos().size() == 0) {
            return null;
        }

        log.info("card calculation {}", bucketDto);
        Double rawReceiptTotalPrice = bucketDto.getProductDtos().stream()
                .map(ProductDto::getTotalPrice)
                .filter(Objects::nonNull)
                .reduce(Double::sum)
                .orElse(0.0);

        Card card = bucketDto.getCard();

        if (Objects.nonNull(card) && rawReceiptTotalPrice > 0) {
            Integer discount = card.getDiscount();
            if (Objects.nonNull(discount) && discount > 0) {

                Double discountedTotalPrice = MathOperations
                        .subPercentageFromNumber(rawReceiptTotalPrice.floatValue(), discount);
                bucketDto.setDiscountSum(rawReceiptTotalPrice - discountedTotalPrice);
                rawReceiptTotalPrice = discountedTotalPrice;
            }
        }
        bucketDto.setReceivedSum(rawReceiptTotalPrice);

        return bucketDto;
    }

}
