package com.example.EcoHack.exceptions.api;

import com.example.EcoHack.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class UserAccountAlreadyActivatedException extends BaseException {
    public UserAccountAlreadyActivatedException() {
        super(HttpStatus.CONFLICT, "This user account is already activated!");
    }
}
