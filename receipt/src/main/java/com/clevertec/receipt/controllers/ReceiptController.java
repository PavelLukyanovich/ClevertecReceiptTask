package com.clevertec.receipt.controllers;

import com.clevertec.receipt.models.requests.ReceiptRequest;
import com.clevertec.receipt.services.ReceiptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Slf4j
@RestController
public class ReceiptController {

    private static final String HEADER_CONTENT_DISPOSITION = "attachment; filename=response.txt";

    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {

        this.receiptService = receiptService;
    }

    @PostMapping(value = "/receipt", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getReceipt(@RequestBody ReceiptRequest receiptRequest) {

        log.info("from getReceipt id = {}", receiptRequest);
        String receipt = receiptService.getReceipt(receiptRequest);
        return ResponseEntity
                .created(URI.create("/receipt"))
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, HEADER_CONTENT_DISPOSITION)
                .body(receipt);
    }
}