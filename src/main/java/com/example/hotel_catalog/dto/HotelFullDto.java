package com.example.hotel_catalog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Schema(description = "Модель отеля со всей информацией")
public class HotelFullDto {

    @Schema(description = "Идентификатор модели отеля", example = "1")
    private Long id;

    @Schema(description = "Название отеля", example = "DoubleTree by Hilton Minsk")
    private String name;

    @Schema(description = "Описание отеля", example = "The DoubleTree by Hilton Hotel Minsk offers 193...")
    private String description;

    @Schema(description = "Название бренда отеля", example = "Hilton")
    private String brand;

    @Schema(description = "Модель адреса отеля")
    private AddressDto address;

    @Schema(description = "Модель контактов отеля")
    private ContactDto contacts;

    @Schema(description = "Модель времени прибытия в отель")
    private ArrivalTimeDto arrivalTime;

    @Schema(description = "Список удобств", example = "[Free Wi-Fi, Free parking]")
    private List<String> amenities;

}
