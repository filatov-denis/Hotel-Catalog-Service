package com.example.hotel_catalog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Модель времени прибытия в отель")
public class ArrivalTimeDto {

    @JsonFormat(pattern = "HH:mm")
    @NotNull(message = "Время начала прибытия не может быть пустым")
    @Schema(description = "Дата рождения", example = "12:00")
    private LocalTime checkIn;

    @JsonFormat(pattern = "HH:mm")
    @Schema(description = "Время окончания прибытия", example = "14:00")
    private LocalTime checkOut;

}
