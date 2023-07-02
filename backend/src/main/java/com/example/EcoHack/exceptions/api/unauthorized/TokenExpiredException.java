package com.example.EcoHack.exceptions.api.unauthorized;

import com.example.EcoHack.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class TokenExpiredException extends BaseException {
    public TokenExpiredException(com.auth0.jwt.exceptions.TokenExpiredException rootTokenExpiredException) {
        super(HttpStatus.UNAUTHORIZED, rootTokenExpiredException.getMessage());
        setRootException(rootTokenExpiredException);
    }
    // removed because this causes inconsistency in ex. messages if used incorrectly
    //    public TokenExpiredException(){
    //        super(HttpStatus.UNAUTHORIZED, "Authentication Failed! Token Expired!");
    //    }
}
