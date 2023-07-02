package com.example.EcoHack.exceptions.api;

import com.example.EcoHack.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class ConfirmationTokenNotFoundException extends BaseException {
    public ConfirmationTokenNotFoundException() {
        super(HttpStatus.CONFLICT, "The confirmation token is invalid, has already been used or is expired!");
    }
}
