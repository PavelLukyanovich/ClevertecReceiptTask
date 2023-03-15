package com.clevertec.receipt.controllers;

import com.clevertec.receipt.exceptions.NoSuchElementsException;
import com.clevertec.receipt.exceptions.ReceiptJsonParseException;
import com.clevertec.receipt.models.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ReceiptExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementsException.class)
    public ErrorResponse handlerNoSuchElementsException(NoSuchElementsException e) {

        log.info("Fetched NoSuchElementsException idsList={}", e.getNotFoundedProductIds());
        return new ErrorResponse(HttpStatus.NOT_FOUND,"Products with current ids not founded", e.getNotFoundedProductIds());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ReceiptJsonParseException.class)
    public ErrorResponse handlerNoSuchElementsException(ReceiptJsonParseException e) {

        return new ErrorResponse(HttpStatus.BAD_REQUEST,"Something went wrong, pls try again", "");
    }
}