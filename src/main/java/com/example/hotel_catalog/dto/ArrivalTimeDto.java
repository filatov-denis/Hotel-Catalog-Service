package com.example.hotel_catalog.dto;

import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ArrivalTimeDto {

    private LocalTime checkIn;

    private LocalTime checkOut;

}
