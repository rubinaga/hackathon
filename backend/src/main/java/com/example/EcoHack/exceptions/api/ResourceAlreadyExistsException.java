package com.example.EcoHack.exceptions.api;

import com.example.EcoHack.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class ResourceAlreadyExistsException extends BaseException {
    public String resourceName;
    public String fieldName;
    public String fieldValue;

    public ResourceAlreadyExistsException(String resourceName, String fieldName, String fieldValue) {
        super(HttpStatus.CONFLICT, String.format("%s with %s: '%s' already exists.", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
