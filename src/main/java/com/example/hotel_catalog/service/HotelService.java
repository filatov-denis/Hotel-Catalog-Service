package com.example.hotel_catalog.service;

import com.example.hotel_catalog.dto.HotelCreateDto;
import com.example.hotel_catalog.dto.HotelFullDto;
import com.example.hotel_catalog.dto.HotelShortDto;
import com.example.hotel_catalog.exception.EmptyIdException;
import com.example.hotel_catalog.exception.EntityNotExistsException;
import com.example.hotel_catalog.mapper.HotelMapper;
import com.example.hotel_catalog.model.Address;
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

    @Transactional
    public HotelShortDto createHotel(HotelCreateDto createDto) {
        Hotel entity = hotelMapper.toEntity(createDto);
        Address address = entity.getAddress();
        entity.setAddress(null);

        Hotel persisted = hotelRepository.save(entity);
        persisted.setAddress(address);
        address.setHotel(persisted);

        return hotelMapper.toShortDto(hotelRepository.save(persisted));
    }

    public HotelFullDto getById(Long id) {
        return hotelMapper.toFullDto(getHotel(id));
    }

    @Transactional
    public void setNewAmenitiesToHotel(Long id, List<String> newAmenities) {
        Hotel hotel = getHotel(id);
        List<Amenity> hotelStoredList = hotel.getAmenities().stream().toList();

        Set<String> newAmenititesSet = new HashSet<>(newAmenities);

        Map<String, Amenity> allStored = new HashMap<>();
        for(Amenity amenity : amenityRepository.findAll()) {
            allStored.put(amenity.getName(), amenity);
        }

        List<Amenity> removed = new ArrayList<>();
        for(Amenity amenity : hotelStoredList) {
            if(!newAmenititesSet.contains(amenity.getName())) {
                hotel.removeAmenity(amenity);
                removed.add(amenity);
            }
        }

        for(String name : newAmenities) {
            if(allStored.containsKey(name)) {
                Amenity amenity = allStored.get(name);
                hotel.addAmenity(amenity);
            } else {
                hotel.addAmenity(new Amenity(null, name, new LinkedHashSet<>()));
            }
        }

        amenityRepository.saveAll(removed);
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
