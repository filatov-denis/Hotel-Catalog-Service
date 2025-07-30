package com.example.hotel_catalog.controller;

import com.example.hotel_catalog.dto.HotelCreateDto;
import com.example.hotel_catalog.dto.HotelFullDto;
import com.example.hotel_catalog.dto.HotelShortDto;
import com.example.hotel_catalog.service.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/property-view/hotels")
@Tag(name = "Отели", description = "Содержит базовые операции, связанные с отелями")
public class HotelController {

    private final HotelService hotelService;

    @GetMapping
    @Operation(summary = "Получить все отели", description = "Выводит список всех отелей с краткой иинформацией")
    public List<HotelShortDto> getAll() {
        return hotelService.getAll();
    }

    @GetMapping("{id}")
    @Operation(summary = "Получить конкретный отель", description = "Выводит подробную информацию по конкретному отелю")
    public HotelFullDto getById(@PathVariable Long id) {
        return hotelService.getById(id);
    }

    @PostMapping
    @Operation(summary = "Добавление отеля", description = "Добавляет новый отель на основе тела запроса")
    public HotelShortDto createHotel(@RequestBody @Valid HotelCreateDto dto) {
        return hotelService.createHotel(dto);
    }

    @PostMapping("{id}/amenities")
    //todo добавить описание запроса
    @Operation(summary = "", description = "")
    public void setNewAmenitiesToHotel(@PathVariable Long id, @RequestBody List<String> newAmenities) {
        hotelService.setNewAmenitiesToHotel(id, newAmenities);
    }

}
