package com.example.EcoHack.exceptions.to_refactor;

import com.example.EcoHack.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class InvalidValueException extends BaseException {
    public InvalidValueException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
