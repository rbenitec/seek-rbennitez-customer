package com.seek.rbenitez.customer.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class AuthenticationException extends RuntimeException {
    private String code;
    private HttpStatus httpStatus;

    public AuthenticationException(String code, String message, HttpStatus httpStatus) {
        super(message);
        this.code = code;
        this.httpStatus = httpStatus;
    }
}
