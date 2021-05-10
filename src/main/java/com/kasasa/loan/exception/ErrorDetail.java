package com.kasasa.loan.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorDetail {
    private String fieldName;
    private String errorMessage;
}
