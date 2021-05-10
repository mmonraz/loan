package com.kasasa.loan.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

/**
 * @Author Miguel Monraz
 * This is class is intended to provide a custom exception response
 * when a 404, 400 or any exception happens which will be more useful
 * to the user
 */
public class CustomException {
    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime zonedDateTime;

    public CustomException(String message, HttpStatus httpStatus, ZonedDateTime zonedDateTime) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.zonedDateTime = zonedDateTime;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getZonedDateTime() {
        return zonedDateTime;
    }
}
