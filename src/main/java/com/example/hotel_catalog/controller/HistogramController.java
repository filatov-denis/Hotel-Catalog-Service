package com.example.hotel_catalog.controller;

import com.example.hotel_catalog.dto.HistogramResult;
import com.example.hotel_catalog.service.HistogramService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/property-view/histogram")
@Tag(name = "Гистограммы", description = "Содержит операции, связанные с получением информации об отелях в виде гистонрамм")
public class HistogramController {

    private final HistogramService histogramService;

    @GetMapping("/{param}")
    @Operation(summary = "Гистограмма по одному параметру", description = "Позволяет получить гистограмму количества указанного параметра среди всех отелей")
    public Map<String, Long> getSingleParamHistogram(@PathVariable String param) {
        Map<String, Long> map = HistogramResult.mapData(histogramService.getSingleParamHistogram(param));

        return map;
    }

}
