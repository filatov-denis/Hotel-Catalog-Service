package com.example.hotel_catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HistogramResult {

    private String name;

    private Long count;

    public static Map<String, Long> mapData(List<HistogramResult> data) {
        Map<String, Long> result = new HashMap<>();
        for(HistogramResult res : data) result.put(res.name, res.count);

        return result;
    }

}
