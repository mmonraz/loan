package com.kasasa.loan.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

/**
 * @Author Miguel Monraz
 * This is class is intended to provide a custom exception response
 * when a 404, 400 or any exception happens which will be more useful
 * to the user
 */
@Getter
@AllArgsConstructor
public class CustomException {
    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime zonedDateTime;
}
