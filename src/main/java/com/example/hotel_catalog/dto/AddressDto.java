package com.example.hotel_catalog.dto;

import com.example.hotel_catalog.model.Address;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Модель адреса отеля")
public class AddressDto {

    @NotNull(message = "Номер дома не может быть пустым")
    @Schema(description = "Номер дома", example = "9")
    private Short houseNumber;

    @NotNull(message = "Наименование улицы не может быть пустым")
    @Size(max = 250, message = "Длина имени отеля не может превышать 250 символов")
    @Schema(description = "Наименование улицы", example = "Pobediteley Avenue")
    private String street;

    @NotNull(message = "Наименование города не может быть пустым")
    @Size(max = 250, message = "Длина имени отеля не может превышать 250 символов")
    @Schema(description = "Наименование города", example = "Minsk")
    private String city;

    @NotNull(message = "Название страны не может быть пустым")
    @Size(max = 250, message = "Длина имени отеля не может превышать 250 символов")
    @Schema(description = "Название страны", example = "Belarus")
    private String country;

    @NotNull(message = "Номер дома не может быть пустым")
    @Size(max = 50, message = "Длина почтового индекса не может превышать 50 символов")
    @Schema(description = "Постовый индекс", example = "220009")
    private String postCode;

    private AddressDto(Address address) {
        this.city = address.getCity();
        this.country = address.getCountry();
        this.houseNumber = getHouseNumber();
        this.postCode = address.getPostCode();
        this.street = address.getStreet();
    }

}
