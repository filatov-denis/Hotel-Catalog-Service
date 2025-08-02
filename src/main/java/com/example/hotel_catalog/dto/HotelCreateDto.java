package com.example.hotel_catalog.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class HotelCreateDto {

    private String name;

    private String description;

    private String brand;

    private AddressDto address;

    private ContactDto contacts;

    private ArrivalTimeDto arrivalTime;

}
