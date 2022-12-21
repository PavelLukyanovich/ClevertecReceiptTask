package com.clevertec.receipt.services.receipt;

import com.clevertec.receipt.models.requests.ReceiptRequest;
import org.springframework.stereotype.Service;

public interface ReceiptStorageService {

    void save(ReceiptRequest receiptRequest);
}
