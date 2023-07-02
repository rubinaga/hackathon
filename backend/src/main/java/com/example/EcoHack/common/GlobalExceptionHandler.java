package com.example.EcoHack.common;

import com.example.EcoHack.exceptions.BaseException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Object> invalidCriteriaOperatorExceptionHandler(BaseException ex) {
        return ex.toResponseEntity();
    }


}
