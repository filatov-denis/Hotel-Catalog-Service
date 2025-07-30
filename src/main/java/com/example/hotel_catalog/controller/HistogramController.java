package com.example.hotel_catalog.controller;

import com.example.hotel_catalog.service.HistogramService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/property-view/histogram")
@Tag(name = "Гистограммы", description = "Содержит операции, связанные с получением информации об отелях в виде гистонрамм")
public class HistogramController {

    private final HistogramService histogramService;

    @GetMapping("/transfer/{param}")
    @Operation(summary = "Гистограмма по одному параметру", description = "Позволяет получить гистограмму количества указанного параметра среди всех отелей")
    public Map<String, Integer> getSingleParamHistogram(@PathVariable String param) {
        return histogramService.getSingleParamHistogram(param);
    }

}
