package com.example.EcoHack.exceptions.api.unauthorized;

import com.example.EcoHack.exceptions.BaseException;
import com.example.EcoHack.exceptions.generic.TokenDecodeException;
import org.springframework.http.HttpStatus;

public class InvalidTokenException extends BaseException {
    public InvalidTokenException(TokenDecodeException rootTokenDecodeException) {
        super(HttpStatus.UNAUTHORIZED, "Could not decode token!");
        setRootException(rootTokenDecodeException);
    }

    public InvalidTokenException(String message, Exception rootException) {
        super(HttpStatus.UNAUTHORIZED, message);
        setRootException(rootException);
    }

    public InvalidTokenException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}

