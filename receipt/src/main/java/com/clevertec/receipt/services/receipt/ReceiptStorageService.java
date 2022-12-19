package com.clevertec.receipt.services.receipt;

import com.clevertec.receipt.models.requests.ReceiptRequest;

public interface ReceiptStorageService {

    void save(ReceiptRequest receiptRequest);
}
