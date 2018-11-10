package com.myexperiments.springmvc.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        code = HttpStatus.NOT_FOUND, reason = "The spitter already exists"
)
public class DuplicateSpitterException extends RuntimeException {

    private String customErrorMessage;

    public DuplicateSpitterException(String customErrorMessage) {
        this.customErrorMessage = customErrorMessage;
    }

    public String getCustomErrorMessage() {
        return customErrorMessage;
    }
}
