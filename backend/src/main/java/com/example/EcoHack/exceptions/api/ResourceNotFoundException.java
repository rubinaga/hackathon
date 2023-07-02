package com.example.EcoHack.exceptions.api;

import com.example.EcoHack.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BaseException {
    public String resourceName;
    public String fieldName;
    public Object fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(HttpStatus.NOT_FOUND, String.format("%s with %s: %s does not exist.", resourceName, fieldName, fieldValue.toString()));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
