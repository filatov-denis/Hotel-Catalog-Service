package com.example.hotel_catalog.mapper;

import com.example.hotel_catalog.dto.*;
import com.example.hotel_catalog.model.Amenity;
import com.example.hotel_catalog.model.Hotel;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Mapper(builder = @Builder(disableBuilder = true), uses = AddressMapper.class)
public interface HotelMapper {

    String TO_ARRIVAL_DTO_EXPRESSION = "java(mapArrivalTime(entity))";

    String TO_CONTACT_EXPRESSION = "java(mapContacts(entity))";

    String TO_ADDRESS_STRING_EXPRESSION = "java(entity.getAddress().toFormattedString())";

    String TO_AMENITIES_LIST = "toAmenitiesList";

    String CONTACTS_NAME = "contacts";

    String ARRIVAL_TIME_NAME = "arrivalTime";

    String AMENITIES_NAME = "amenities";

    String ADDRESS_NAME = "address";

    HotelMapper INSTANCE = Mappers.getMapper(HotelMapper.class);

    List<HotelShortDto> toListShortDto(List<Hotel> entityList);

    @Mapping(target = ADDRESS_NAME, expression = TO_ADDRESS_STRING_EXPRESSION)
    HotelShortDto toShortDto(Hotel entity);

    @Mapping(target = CONTACTS_NAME, expression = TO_CONTACT_EXPRESSION)
    @Mapping(target = ARRIVAL_TIME_NAME, expression = TO_ARRIVAL_DTO_EXPRESSION)
    @Mapping(target = AMENITIES_NAME, qualifiedByName = TO_AMENITIES_LIST)
    HotelFullDto toFullDto(Hotel entity);

    default Hotel toEntity(HotelCreateDto createDto) {
        Hotel hotel = new Hotel();

        hotel.setBrand(createDto.getBrand());
        hotel.setName(createDto.getName());
        hotel.setDescription(createDto.getDescription());

        hotel.setAddress(AddressMapper.INSTANCE.toEntity(createDto.getAddress()));

        hotel.setEmail(createDto.getContacts().getEmail());
        hotel.setPhone(createDto.getContacts().getPhone());

        hotel.setCheckIn(createDto.getArrivalTime().getCheckIn());
        hotel.setCheckOut(createDto.getArrivalTime().getCheckOut());

        return hotel;
    }

    @Named(TO_AMENITIES_LIST)
    default List<String> map(Set<Amenity> value) {
        List<String> result = new ArrayList<>();

        for(Amenity amenity : value) result.add(amenity.getName());

        return result;
    }

    default ContactDto mapContacts(Hotel entity) {
        return new ContactDto(entity.getPhone(), entity.getEmail());
    }

    default ArrivalTimeDto mapArrivalTime(Hotel entity) {
        return new ArrivalTimeDto(entity.getCheckIn(), entity.getCheckOut());
    }
}
