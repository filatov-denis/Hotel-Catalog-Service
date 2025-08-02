package com.example.hotel_catalog.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class HotelFullDto {

    private Long id;

    private String name;

    private String description;

    private String brand;

    private AddressDto address;

    private ContactDto contacts;

    private ArrivalTimeDto arrivalTime;

    private List<String> amenities;

}
