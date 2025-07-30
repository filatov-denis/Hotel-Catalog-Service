package com.example.hotel_catalog.service;

import com.example.hotel_catalog.dto.HotelCreateDto;
import com.example.hotel_catalog.dto.HotelFullDto;
import com.example.hotel_catalog.dto.HotelShortDto;
import com.example.hotel_catalog.mapper.HotelMapper;
import com.example.hotel_catalog.model.Hotel;
import com.example.hotel_catalog.repository.AddressRepository;
import com.example.hotel_catalog.repository.AmenityRepository;
import com.example.hotel_catalog.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;

    private final AddressRepository addressRepository;

    private final AmenityRepository amenityRepository;

    private final HotelMapper hotelMapper = HotelMapper.INSTANCE;

    public HotelShortDto createHotel(HotelCreateDto createDto) {
        //todo
        Hotel entity = hotelMapper.toEntity(createDto);

        return hotelMapper.toShortDto(hotelRepository.save(entity));
    }

    public HotelFullDto getById(Long id) {
        Optional<Hotel> optional = hotelRepository.findById(id);
        //todo exception
        return hotelMapper.toFullDto(optional.get());
    }

    public void setNewAmenitiesToHotel(Long id, List<String> newAmenities) {
        //todo
    }

    public List<HotelShortDto> getAll() {
        return hotelMapper.toListShortDto(hotelRepository.findAll());
    }

}
