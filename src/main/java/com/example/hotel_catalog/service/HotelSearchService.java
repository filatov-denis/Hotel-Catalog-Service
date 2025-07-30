package com.example.hotel_catalog.service;

import com.example.hotel_catalog.dto.HotelShortDto;
import com.example.hotel_catalog.filter.HotelParameterizedFilter;
import com.example.hotel_catalog.mapper.HotelMapper;
import com.example.hotel_catalog.repository.AddressDynamicQuerySelector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelSearchService {

    private final AddressDynamicQuerySelector querySelector;

    private final HotelMapper hotelMapper = HotelMapper.INSTANCE;

    public List<HotelShortDto> getFilteredData(HotelParameterizedFilter filter) {
        return hotelMapper.toListShortDto(querySelector.getByDynamicFilter(filter));
    }

}
