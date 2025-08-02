package com.example.hotel_catalog.mapper;

import com.example.hotel_catalog.dto.AddressDto;
import com.example.hotel_catalog.model.Address;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(builder = @Builder(disableBuilder = true))
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    //AddressDto toDto(Address entity);

    Address toEntity(AddressDto dto);

}
