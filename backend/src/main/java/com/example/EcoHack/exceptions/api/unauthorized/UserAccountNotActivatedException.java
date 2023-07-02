package com.example.EcoHack.exceptions.api.unauthorized;

import com.example.EcoHack.exceptions.BaseException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class UserAccountNotActivatedException extends BaseException {
    public UserAccountNotActivatedException() {
        super(HttpStatus.UNAUTHORIZED, "This user account is not enabled! Please make sure you have verified your email address.");
    }
}
