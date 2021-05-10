package com.kasasa.loan.exception;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class Error {
    private final int status;
    private final String message;
    private List<ErrorDetail> fieldErrors = new ArrayList<>();

    public void addFieldError(String fieldName, String defaultMessage) {
        fieldErrors.add(new ErrorDetail(fieldName, defaultMessage));
    }
}
