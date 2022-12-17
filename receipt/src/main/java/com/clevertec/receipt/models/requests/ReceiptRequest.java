package com.clevertec.receipt.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class ReceiptRequest {
    private String cardNumber;

    private List<Item> items;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Item {
        private Long id;
        private Integer amount;
    }
}
