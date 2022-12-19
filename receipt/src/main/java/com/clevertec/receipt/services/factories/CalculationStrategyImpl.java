package com.clevertec.receipt.services.factories;

import com.clevertec.receipt.models.dto.BucketDto;
import com.clevertec.receipt.services.calculations.CardCalculationProductService;
import com.clevertec.receipt.services.calculations.QuantityCalculationProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CalculationStrategyImpl implements CalculationStrategy {

    private final CardCalculationProductService cardCalculation;

    private final QuantityCalculationProductService quantityCalculation;

    @Override
    public void process(BucketDto bucketDto) {

        quantityCalculation.calculate(bucketDto);
        cardCalculation.calculate(bucketDto);
    }
}