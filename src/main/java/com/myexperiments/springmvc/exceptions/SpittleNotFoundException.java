package com.myexperiments.springmvc.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
        code = HttpStatus.NOT_FOUND, reason = "The spittle was not found"
)
public class SpittleNotFoundException extends RuntimeException {
}
