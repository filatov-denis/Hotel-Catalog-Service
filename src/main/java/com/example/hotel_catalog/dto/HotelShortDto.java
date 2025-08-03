package com.example.hotel_catalog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@Schema(description = "Краткая модель отеля")
public class HotelShortDto {

    @Schema(description = "Идентификатор модели отеля", example = "1")
    private Long id;

    @Schema(description = "Название отеля", example = "DoubleTree by Hilton Minsk")
    private String name;

    @Schema(description = "Описание отеля", example = "The DoubleTree by Hilton Hotel Minsk offers 193...")
    private String description;

    @Schema(description = "Адрес отеля в формате строки", example = "9 Pobediteley Avenue, Minsk, 220004, Belarus")
    private String address;

    @Schema(description = "Телефон отеля", example = "+375 17 309-80-00")
    private String phone;

}
