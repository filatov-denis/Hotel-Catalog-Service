package com.example.hotel_catalog.service;

import com.example.hotel_catalog.exception.UnsupportedHistogramTypeException;
import com.example.hotel_catalog.repository.AddressRepository;
import com.example.hotel_catalog.repository.AmenityRepository;
import com.example.hotel_catalog.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.example.hotel_catalog.constant.FieldName.*;

@Service
@RequiredArgsConstructor
public class HistogramService {

    private final HotelRepository hotelRepository;

    private final AmenityRepository amenityRepository;

    private final AddressRepository addressRepository;

    public Map<String, Integer> getSingleParamHistogram(String paramName) {
        return switch(paramName) {
            case NAME -> hotelRepository.getBrandHistogram();
            case CITY, COUNTRY -> addressRepository.getDataHistogram(paramName);
            case AMENITIES -> amenityRepository.getAmenityHistogram();
            default -> throw new UnsupportedHistogramTypeException();
        };

    }

}
