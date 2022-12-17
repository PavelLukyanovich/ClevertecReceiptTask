package com.clevertec.receipt.controllers;

import com.clevertec.receipt.models.dto.BucketDto;
import com.clevertec.receipt.models.dto.ProductDto;
import com.clevertec.receipt.models.entities.Card;
import com.clevertec.receipt.models.requests.ReceiptRequest;
import com.clevertec.receipt.services.ReceiptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Formatter;

@Slf4j
@RestController
public class ReceiptController {

    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping(value = "/receipt")
    public ResponseEntity<String> getReceipt(@RequestBody ReceiptRequest receiptRequest) {

        log.info("from getReceipt id = {}", receiptRequest);
        String receipt = receiptService.getReceipt(receiptRequest);
        return ResponseEntity
                .accepted()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=response.txt")
                .body(receipt);
    }

    public static File writeObjectToFile(Object obj) throws IOException {

        File file = new File("person.txt");
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(obj);
            oos.flush();
        }

        return file;
    }
/*

    @GetMapping(value = "get", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public FileSystemResource get() throws IOException {
        Card card = new Card();
        card.setCardNumber("11234");
        card.setId(1L);
        return new FileSystemResource((writeObjectToFile(card)));
    }

    @GetMapping(value = "get2", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Object> get2() {


        return ResponseEntity
                .accepted()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=response.txt")
                .body(createReceipt(null));
    }
*/
/*
    public String createReceipt(BucketDto bucketDto) {

        BucketDto dto = BucketDto.builder()
                .discountSum(10.0)
                .receivedSum(120.15)
                .productDtos(Arrays.asList(
                        ProductDto.builder().id(1l).title("Tra ta ta").price(12.4).quantity(2).build(),
                        ProductDto.builder().id(1l).title("Tra ta ta").price(12.4).quantity(2).build(),
                        ProductDto.builder().id(1l).title("Tra ta ta").price(12.4).quantity(2).build(),
                        ProductDto.builder().id(1l).title("Tra ta ta").price(12.4).quantity(2).build(),
                        ProductDto.builder().id(1l).title("Tra ta ta").price(12.4).quantity(2).build(),
                        ProductDto.builder().id(1l).title("Tra ta ta").price(12.4).quantity(2).build()
                ))
                .build();
        Formatter formatter = new Formatter();

        String itemFormat = "%-15s %5s %10s\n";
        formatter.format(itemFormat, "QTY", "ITEM", "PRICE");
        dto.getProductDtos()
                .forEach(productDto -> formatter.format(itemFormat, productDto.getQuantity(), productDto.getTitle(), productDto.getTotalPrice()));

        return formatter.toString();
    }*/
}
