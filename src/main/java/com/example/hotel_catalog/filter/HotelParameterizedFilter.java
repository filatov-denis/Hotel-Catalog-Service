package com.example.hotel_catalog.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Параметры для фильтрации отелей")
public class HotelParameterizedFilter {

    @Size(max = 250, message = "Длина имени отеля не может превышать 250 символов")
    @Schema(description = "Наименование отеля", example = "DoubleTree by Hilton Minsk")
    private String name;

    @Size(max = 250, message = "Длина бренда не может превышать 250 символов")
    @Schema(description = "Бренд", example = "Hilton")
    private String brand;

    @Size(max = 250, message = "Длина города не может превышать 250 символов")
    @Schema(description = "Город", example = "Berlin")
    private String city;

    @Size(max = 250, message = "Длина страны превышать 250 символов")
    @Schema(description = "Страна", example = "Germany")
    private String country;

    @Size(max = 250, message = "Длина наименования удобств не может превышать 250 символов")
    @Schema(description = "Удобства", example = "Fitness center")
    private String amenities;

}
