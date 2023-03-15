package com.clevertec.receipt.models.response;

import org.springframework.http.HttpStatus;

public record ErrorResponse(String message, Object errorObject) {

}
