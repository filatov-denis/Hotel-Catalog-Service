package com.example.hotel_catalog.service;

import com.example.hotel_catalog.mapper.HotelMapper;
import com.example.hotel_catalog.repository.AddressRepository;
import com.example.hotel_catalog.repository.AmenityRepository;
import com.example.hotel_catalog.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class HistogramService {

    private HotelRepository hotelRepository;

    private AmenityRepository amenityRepository;

    private AddressRepository addressRepository;

    //private final HotelMapper hotelMapper = HotelMapper.INSTANCE;

    public Map<String, Integer> getSingleParamHistogram(String paramName) {
        //todo

        return null;
    }

}
