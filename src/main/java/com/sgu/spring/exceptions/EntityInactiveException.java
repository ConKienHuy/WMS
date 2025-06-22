package com.sgu.spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class EntityInactiveException extends RuntimeException {
    public EntityInactiveException(String message) {
        super(message);
    }
}
