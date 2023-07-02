package com.example.EcoHack.exceptions.api.badRequest;

import com.example.EcoHack.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class BadRequestBodyException extends BaseException {

    public BadRequestBodyException(Exception rootException) {
        super(HttpStatus.BAD_REQUEST, String.format("Invalid Request Body!"));
        setRootException(rootException);
    }

    public BadRequestBodyException() {
        this(null);
    }
}
