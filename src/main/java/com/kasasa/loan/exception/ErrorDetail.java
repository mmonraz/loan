package com.kasasa.loan.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author Miguel Monraz
 * This class helps to give the user more detail about
 * the error what was the field and how it was invalid
 */
@Data
@AllArgsConstructor
public class ErrorDetail {
    private String fieldName;
    private String errorMessage;
}
