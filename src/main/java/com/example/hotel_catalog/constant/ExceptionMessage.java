package com.example.hotel_catalog.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionMessage {

    EMPTY_ID("Request with necessary ID does not include ID"),
    ENTITY_NOT_EXISTS("Entity with current ID not exists"),
    UNSUPPORTED_HISTOGRAM_TYPE("Current histogram type not supported"),
    UNEXPECTED_ERROR("An unexpected error occurred");

    private String value;

}
