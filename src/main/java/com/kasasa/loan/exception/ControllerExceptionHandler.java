package com.kasasa.loan.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * @author Miguel Monraz
 * This class will intercept all the HTTP errors and will create
 * logs for the same
 */
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ControllerExceptionHandler {


    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    @ExceptionHandler(LoanNotFoundException.class)
    public ResponseEntity<Object> handleNotFound(LoanNotFoundException ex) {
        HttpStatus notFoundStatus = HttpStatus.NOT_FOUND;
        CustomException customException = new CustomException(ex.getMessage(), notFoundStatus, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(customException, notFoundStatus);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    @ExceptionHandler(InvalidLoanRequestException.class)
    public ResponseEntity<Object> handleBadRequest(InvalidLoanRequestException ex) {
        HttpStatus badRequestStatus = HttpStatus.BAD_REQUEST;
        CustomException customException = new CustomException(ex.getMessage(), badRequestStatus, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(customException, badRequestStatus);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    @ExceptionHandler(Exception.class)
    public void handleGeneralError(Exception ex) {
        log.error("An error occurred procesing request" + ex);
    }

    /**
     * @Author Miguel Monraz
     * @param exception
     * @return An Error Object which contains the status plus the list of any invalid
     *  field sent on the request body
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected Error handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception) {

        BindingResult result = exception.getBindingResult();

        List<FieldError> fieldErrors = result.getFieldErrors();

        Error error = new Error(HttpStatus.BAD_REQUEST.value(), "validation errors");
        for (FieldError fieldError: fieldErrors) {
            error.addFieldError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return error;
    }
}
