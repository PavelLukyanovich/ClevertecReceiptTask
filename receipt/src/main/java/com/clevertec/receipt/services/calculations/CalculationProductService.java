package com.clevertec.receipt.services.calculations;

import com.clevertec.receipt.models.dto.BucketDto;

public interface CalculationProductService {

    BucketDto calculate(BucketDto bucketDto);

}
