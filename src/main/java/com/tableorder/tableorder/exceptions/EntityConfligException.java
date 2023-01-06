package com.tableorder.tableorder.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EntityConfligException extends RuntimeException {

    public EntityConfligException(String message) {
        super(message);
    }
}
