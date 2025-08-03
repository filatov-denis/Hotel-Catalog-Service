package com.example.hotel_catalog.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Модель добавления отеля")
public class HotelCreateDto {

    @NotNull(message = "Название отеля не может быть пустым")
    @Size(max = 250, message = "Длина названия отеля не может превышать 250 символов")
    @Schema(description = "Название отеля", example = "DoubleTree by Hilton Minsk")
    private String name;

    @Schema(description = "Описание отеля", example = "The DoubleTree by Hilton Hotel Minsk offers 193...")
    private String description;

    @NotNull(message = "Название бренда  не может быть пустым")
    @Size(max = 50, message = "Длина названия бренда не может превышать 50 символов")
    @Schema(description = "Название бренда", example = "Hilton")
    private String brand;

    @Valid
    @NotNull(message = "Информация о адресе отеля не может быть пустой")
    @Schema(description = "Информация о адресе отеля")
    private AddressDto address;

    @Valid
    @NotNull(message = "Контактная информация отеля не может быть пустой")
    @Schema(description = "Контактная информация отеля")
    private ContactDto contacts;

    @Valid
    @NotNull(message = "Информация о времени прибытия не может быть пустой")
    @Schema(description = "Информация о времени прибытия")
    private ArrivalTimeDto arrivalTime;

}

