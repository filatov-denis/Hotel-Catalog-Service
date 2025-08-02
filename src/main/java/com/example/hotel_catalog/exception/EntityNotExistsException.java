package com.example.hotel_catalog.exception;

import com.example.hotel_catalog.constant.ExceptionMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EntityNotExistsException extends ServiceException {
    public EntityNotExistsException() {
        super(ExceptionMessage.ENTITY_NOT_EXISTS.getValue());
        this.statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
