package com.example.hotel_catalog.mapper;

import com.example.hotel_catalog.dto.HotelCreateDto;
import com.example.hotel_catalog.dto.HotelFullDto;
import com.example.hotel_catalog.dto.HotelShortDto;
import com.example.hotel_catalog.model.Hotel;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(builder = @Builder(disableBuilder = true))
public interface HotelMapper {

    HotelMapper INSTANCE = Mappers.getMapper(HotelMapper.class);

    List<HotelShortDto> toListShortDto(List<Hotel> entityList);

    HotelShortDto toShortDto(Hotel entity);

    HotelFullDto toFullDto(Hotel entity);

    Hotel toEntity(HotelCreateDto createDto);

}
