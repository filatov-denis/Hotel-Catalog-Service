package com.example.hotel_catalog.controller;

import com.example.hotel_catalog.dto.HotelShortDto;
import com.example.hotel_catalog.filter.HotelParameterizedFilter;
import com.example.hotel_catalog.service.HotelSearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/property-view/search")
@Tag(name = "Поиск", description = "Содержит различные поисковые операции")
public class SearchController {

    public final HotelSearchService searchService;

    @GetMapping
    @Operation(summary = "Поиск по фильтру", description = "Выводит отфильтрованный список отелей с их краткой информацией")
    public List<HotelShortDto> getFilteredData(@Valid HotelParameterizedFilter filter) {
        return searchService.getFilteredData(filter);
    }

}
