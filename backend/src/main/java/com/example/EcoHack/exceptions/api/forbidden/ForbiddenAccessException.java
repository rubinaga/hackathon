package com.example.EcoHack.exceptions.api.forbidden;

import com.example.EcoHack.exceptions.BaseException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ForbiddenAccessException extends BaseException {
    public ForbiddenAccessException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }
}
