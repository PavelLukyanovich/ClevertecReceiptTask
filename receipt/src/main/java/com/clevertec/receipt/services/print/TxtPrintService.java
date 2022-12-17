package com.clevertec.receipt.services.print;

import com.clevertec.receipt.models.dto.BucketDto;

public interface TxtPrintService {

    String createReceipt(BucketDto bucketDto);
}
