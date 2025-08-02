package com.example.hotel_catalog.exception;

import com.example.hotel_catalog.constant.ExceptionMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UnsupportedHistogramTypeException extends ServiceException {

    public UnsupportedHistogramTypeException() {
        super(ExceptionMessage.UNSUPPORTED_HISTOGRAM_TYPE.getValue());
        this.statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
