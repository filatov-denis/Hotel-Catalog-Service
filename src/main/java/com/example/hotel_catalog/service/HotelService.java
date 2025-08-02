package com.example.hotel_catalog.service;

import com.example.hotel_catalog.dto.HotelCreateDto;
import com.example.hotel_catalog.dto.HotelFullDto;
import com.example.hotel_catalog.dto.HotelShortDto;
import com.example.hotel_catalog.exception.EmptyIdException;
import com.example.hotel_catalog.exception.EntityNotExistsException;
import com.example.hotel_catalog.mapper.HotelMapper;
import com.example.hotel_catalog.model.Amenity;
import com.example.hotel_catalog.model.Hotel;
import com.example.hotel_catalog.repository.AmenityRepository;
import com.example.hotel_catalog.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;

    private final AmenityRepository amenityRepository;

    private final HotelMapper hotelMapper = HotelMapper.INSTANCE;

    public HotelShortDto createHotel(HotelCreateDto createDto) {
        Hotel entity = hotelMapper.toEntity(createDto);

        return hotelMapper.toShortDto(hotelRepository.save(entity));
    }

    public HotelFullDto getById(Long id) {
        return hotelMapper.toFullDto(getHotel(id));
    }

    @Transactional
    public void setNewAmenitiesToHotel(Long id, List<String> newAmenities) {
        Hotel hotel = getHotel(id);
        List<Amenity> allStored = amenityRepository.findAll();
        List<Amenity> hotelStored = hotel.getAmenities().stream().toList();

        List<String> notContained = new ArrayList<>();
        for(String name : newAmenities) {
            for(Amenity amenity : hotelStored) {
                if(amenity.getName().equals(name)) break;
            }

            notContained.add(name);
        }

        for(String name : notContained) {
            for(Amenity amenity : allStored) {
                if(amenity.getName().equals(name)) hotel.addAmenity(amenity);

                break;
            }

            hotel.addAmenity(new Amenity(null, name, new LinkedHashSet<>()));
        }

        hotelRepository.save(hotel);
    }

    private Hotel getHotel(Long id) {
        if(id == null) throw new EmptyIdException();

        Optional<Hotel> optional = hotelRepository.findById(id);
        if(optional.isEmpty()) throw new EntityNotExistsException();

        return optional.get();
    }

    public List<HotelShortDto> getAll() {
        return hotelMapper.toListShortDto(hotelRepository.findAll());
    }

}
