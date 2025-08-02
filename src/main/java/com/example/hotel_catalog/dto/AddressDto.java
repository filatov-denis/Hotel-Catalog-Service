package com.example.hotel_catalog.dto;

import com.example.hotel_catalog.model.Address;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private Short houseNumber;

    private String street;

    private String city;

    private String country;

    private String postCode;

    private AddressDto(Address address) {
        this.city = address.getCity();
        this.country = address.getCountry();
        this.houseNumber = getHouseNumber();
        this.postCode = address.getPostCode();
        this.street = address.getStreet();
    }

}
