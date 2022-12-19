package com.clevertec.receipt.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class NoSuchElementsException extends RuntimeException {

    private final List<Long> notFoundedProductIds;

    public NoSuchElementsException(List<Long> notFoundedProductIds) {

        this.notFoundedProductIds = notFoundedProductIds;
    }
}
