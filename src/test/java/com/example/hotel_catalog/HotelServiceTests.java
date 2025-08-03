package com.example.hotel_catalog;

import com.example.hotel_catalog.dto.*;
import com.example.hotel_catalog.exception.EmptyIdException;
import com.example.hotel_catalog.exception.EntityNotExistsException;
import com.example.hotel_catalog.mapper.AddressMapper;
import com.example.hotel_catalog.service.HotelService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HotelServiceTests {

    @Autowired
    private HotelService hotelService;

    private final AddressMapper addressMapper = AddressMapper.INSTANCE;

    private final HotelCreateDto first;

    private final HotelCreateDto second;

    public HotelServiceTests() {
        first = HotelCreateDto.builder().name("DoubleTree by Hilton Minsk")
                .description("The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the Belorussian capital and stunning views of Minsk city from the hotel's 20th floor ...")
                .brand("Hilton")
                .address(AddressDto.builder()
                        .houseNumber((short) 9)
                        .street("Pobediteley Avenue")
                        .city("Minsk")
                        .country("Belarus")
                        .postCode("220004").build())
                .contacts(ContactDto.builder()
                        .email("doubletreeminsk.info@hilton.com")
                        .phone("+375 17 309-80-00").build())
                .arrivalTime(ArrivalTimeDto.builder()
                        .checkIn(LocalTime.of(14, 0))
                        .checkOut(LocalTime.of(12, 0))
                        .build())
                .build();

        second = HotelCreateDto.builder().name("Royalty by Access Minsk")
                .brand("Access")
                .address(AddressDto.builder()
                        .houseNumber((short) 29)
                        .street("Pobediteley Avenue")
                        .city("Minsk")
                        .country("Belarus")
                        .postCode("220730").build())
                .contacts(ContactDto.builder()
                        .email("doubletreeminsk.info@hilton.com")
                        .phone("+375 17 389-80-00").build())
                .arrivalTime(ArrivalTimeDto.builder()
                        .checkIn(LocalTime.of(18, 0))
                        .build())
                .build();
    }
    @Test
    @Order(4)
    public void HotelService_Save_ReturnsPersistedHotel() {
        HotelShortDto persisted = hotelService.createHotel(first);
        Assertions.assertNotNull(persisted);
        Assertions.assertNotNull(persisted.getId());

        Assertions.assertEquals(persisted.getName(), first.getName());
        Assertions.assertEquals(persisted.getDescription(), first.getDescription());

        String formattedAddress = addressMapper.toEntity(first.getAddress()).toFormattedString();

        Assertions.assertEquals(persisted.getPhone(), first.getContacts().getPhone());
        Assertions.assertEquals(persisted.getAddress(), formattedAddress);

        HotelShortDto persisted2 = hotelService.createHotel(second);

        Assertions.assertNotNull(persisted2.getId());
        Assertions.assertEquals(persisted2.getName(), second.getName());
        Assertions.assertEquals(persisted2.getDescription(), second.getDescription());
        Assertions.assertEquals(persisted2.getPhone(), second.getContacts().getPhone());
    }

    @Test
    @Order(5)
    public void HotelService_FindById_ReturnsFullHotelData() {
        Assertions.assertEquals(assertThrows(EmptyIdException.class,
                        () -> hotelService.getById(null)).getClass(),
                EmptyIdException.class);

        Assertions.assertEquals(assertThrows(EntityNotExistsException.class,
                        () -> hotelService.getById(912L)).getClass(),
                EntityNotExistsException.class);

        HotelFullDto result = hotelService.getById(5L);

        Assertions.assertNotNull(result.getId());
        Assertions.assertEquals(result.getBrand(), first.getBrand());
        Assertions.assertEquals(result.getDescription(), first.getDescription());

        Assertions.assertEquals(result.getContacts().getPhone(), first.getContacts().getPhone());
        Assertions.assertEquals(result.getContacts().getEmail(), first.getContacts().getEmail());
        Assertions.assertEquals(result.getArrivalTime().getCheckOut(), first.getArrivalTime().getCheckOut());

        Assertions.assertEquals(result.getAddress().getHouseNumber(), first.getAddress().getHouseNumber());
        Assertions.assertEquals(result.getAddress().getCity(), first.getAddress().getCity());
    }

    @Test
    @Order(6)
    public void HotelService_SetAmenities_ReturnsFullHotelData() {
        List<String> amenities = List.of(
                "Free parking",
                "Free WiFi",
                "Non-smoking rooms",
                "Concierge",
                "On-site restaurant",
                "Fitness center",
                "Pet-friendly rooms",
                "Room service",
                "Business center",
                "Meeting rooms"
        );

        Assertions.assertEquals(assertThrows(EmptyIdException.class,
                        () -> hotelService.setNewAmenitiesToHotel(null, amenities)).getClass(),
                EmptyIdException.class);

        Assertions.assertEquals(assertThrows(EntityNotExistsException.class,
                        () -> hotelService.setNewAmenitiesToHotel(912L, amenities)).getClass(),
                EntityNotExistsException.class);

        hotelService.setNewAmenitiesToHotel(5L, amenities);

        HotelFullDto result = hotelService.getById(5L);

        Assertions.assertEquals(amenities.size(), result.getAmenities().size());

        Assertions.assertNotNull(result.getId());
        Assertions.assertTrue(result.getAmenities().contains(amenities.get(0)));
        Assertions.assertTrue(result.getAmenities().contains(amenities.get(1)));
        Assertions.assertTrue(result.getAmenities().contains(amenities.get(2)));
        Assertions.assertTrue(result.getAmenities().contains(amenities.get(5)));
        Assertions.assertTrue(result.getAmenities().contains(amenities.get(9)));

        hotelService.setNewAmenitiesToHotel(5L, new ArrayList<>());

        result = hotelService.getById(5L);

        Assertions.assertEquals(result.getAmenities().size(), 0);
    }

    @Test
    @Order(7)
    public void HotelService_GetAll_ReturnsListOfAllHotels() {
        List<HotelShortDto> result = hotelService.getAll();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.size());
        Assertions.assertNotNull(result.get(0).getId());
        Assertions.assertNotNull(result.get(1).getId());

        Assertions.assertNotNull(result.get(0).getAddress());
        Assertions.assertNotNull(result.get(1).getAddress());
    }

}
