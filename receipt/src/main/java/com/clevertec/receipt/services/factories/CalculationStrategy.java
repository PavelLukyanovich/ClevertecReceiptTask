package com.clevertec.receipt.services.factories;

import com.clevertec.receipt.models.dto.BucketDto;

public interface CalculationStrategy {
    void process(BucketDto bucketDto);
}
