package com.clevertec.receipt.services.receipt;

import com.clevertec.receipt.exceptions.ReceiptJsonParseException;
import com.clevertec.receipt.models.entities.Receipt;
import com.clevertec.receipt.models.requests.ReceiptRequest;
import com.clevertec.receipt.repositories.ReceiptRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReceiptStorageServiceImpl implements ReceiptStorageService {

    private final ReceiptRepository receiptRepository;

    private final ObjectMapper objectMapper;

    @Override
    public void save(ReceiptRequest receiptRequest) {

        try {
            log.info("Save receipt to db");

            String asJsonString = objectMapper.writeValueAsString(receiptRequest);
            Receipt receipt = Receipt.builder()
                    .json(asJsonString)
                    .creationDateTime(LocalDateTime.now())
                    .build();
            Receipt saved = receiptRepository.save(receipt);

            log.info("Save receipt to db, saved id={}", saved.getId());
        } catch (JsonProcessingException e) {
            log.info("can't parse receiptRequest={}", receiptRequest);
            throw new ReceiptJsonParseException();
        }
    }
}
