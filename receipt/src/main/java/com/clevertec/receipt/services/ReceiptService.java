package com.clevertec.receipt.services;

import com.clevertec.receipt.models.requests.ReceiptRequest;

public interface ReceiptService {
    String getReceipt(ReceiptRequest receiptRequest);
}
