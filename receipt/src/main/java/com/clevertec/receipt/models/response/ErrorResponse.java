package com.clevertec.receipt.models.response;

import org.springframework.http.HttpStatus;

public record ErrorResponse(HttpStatus status, String message, Object errorObject) {

}
