package com.example.hotel_catalog.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class HotelShortDto {

    private Long id;

    private String name;

    private String description;

    private String address;

    private String phone;

}
