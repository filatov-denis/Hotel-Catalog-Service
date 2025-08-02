package com.example.hotel_catalog.exception;

import com.example.hotel_catalog.constant.ExceptionMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EmptyIdException extends ServiceException {

    public EmptyIdException() {
        super(ExceptionMessage.EMPTY_ID.getValue());
        this.statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
