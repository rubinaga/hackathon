package com.example.EcoHack.exceptions.api;

import com.example.EcoHack.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class FieldValidationException extends BaseException {

    public FieldValidationException() {
        super(HttpStatus.BAD_REQUEST, "");
    }

}
