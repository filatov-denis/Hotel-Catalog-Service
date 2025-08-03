package com.example.hotel_catalog;

import com.example.hotel_catalog.model.Address;
import com.example.hotel_catalog.model.Hotel;
import com.example.hotel_catalog.repository.AddressRepository;
import com.example.hotel_catalog.repository.HotelRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalTime;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class HotelRepositoryTests {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private AddressRepository addressRepository;

    private final Hotel hotel1;

    private final Hotel hotel2;

    public HotelRepositoryTests() {
        hotel1 = Hotel.builder().name("DoubleTree by Hilton Minsk")
                .description("The DoubleTree by Hilton Hotel Minsk offers 193 luxurious rooms in the Belorussian capital and stunning views of Minsk city from the hotel's 20th floor ...")
                .brand("Hilton")
                .email("doubletreeminsk.info@hilton.com")
                .phone("+375 17 309-80-00")
                .checkIn(LocalTime.of(14, 0))
                .checkOut(LocalTime.of(12, 0))
                .build();

        hotel2 = Hotel.builder().name("Royalty by Access Minsk")
                .brand("Access")
                .email("doubletreeminsk.info@hilton.com")
                .phone("+375 17 389-80-00")
                .checkIn(LocalTime.of(18, 0))
                .build();
    }

    @Test
    @Order(1)
    public void HotelRepository_Save_ReturnsPersistedHotel() {
        Hotel persisted = hotelRepository.save(hotel1);

        Assertions.assertNotNull(persisted);
        Assertions.assertNotNull(persisted.getId());

        Assertions.assertEquals(persisted.getBrand(), hotel1.getBrand());
        Assertions.assertEquals(persisted.getDescription(), hotel1.getDescription());

        Assertions.assertEquals(persisted.getCheckIn(), hotel1.getCheckIn());
        Assertions.assertEquals(persisted.getCheckOut(), hotel1.getCheckOut());
    }

    @Test
    @Order(2)
    public void HotelRepository_Save_ReturnsPersistedHotelWithRestrictions() {
        Hotel persisted = hotelRepository.save(hotel2);

        Assertions.assertNotNull(persisted);
        Assertions.assertNotNull(persisted.getId());

        Assertions.assertEquals(persisted.getBrand(), hotel2.getBrand());
        Assertions.assertEquals(persisted.getDescription(), hotel2.getDescription());

        Assertions.assertEquals(persisted.getCheckIn(), hotel2.getCheckIn());
        Assertions.assertEquals(persisted.getCheckOut(), hotel2.getCheckOut());
    }

    @Test
    @Order(3)
    public void HotelRepository_Save_ReturnsPersistedHotelWithAddress() {
        Hotel persisted1 = hotelRepository.save(hotel1);
        Hotel persisted2 = hotelRepository.save(hotel2);

        Long firstId = persisted1.getId();
        Long secondId = persisted2.getId();

        Address address1 = Address.builder()
                .houseNumber((short) 7)
                .street("Pobediteley Avenue")
                .city("Minsk")
                .country("Belarus")
                .postCode("220004").build();

        persisted1.setAddress(address1);
        address1.setHotel(persisted1);

        Address address2 = Address.builder()
                .houseNumber((short) 29)
                .street("Pobediteley Avenue")
                .city("Minsk")
                .country("Belarus")
                .postCode("220730").build();

        persisted2.setAddress(address2);
        address2.setHotel(persisted2);

        hotelRepository.save(persisted1);
        hotelRepository.save(persisted2);

        Address persistedAddress1 = addressRepository.findById(firstId).get();
        Address persistedAddress2 = addressRepository.findById(secondId).get();

        Assertions.assertNotNull(persistedAddress1);
        Assertions.assertNotNull(persistedAddress2);

        Assertions.assertEquals(persistedAddress1.getId(), persisted1.getId());
        Assertions.assertEquals(persistedAddress2.getId(), persisted2.getId());

        Assertions.assertEquals(persistedAddress1.getCity(), address1.getCity());
        Assertions.assertEquals(persistedAddress2.getCity(), address2.getCity());

        Assertions.assertEquals(persistedAddress1.getCountry(), address1.getCountry());
        Assertions.assertEquals(persistedAddress2.getCountry(), address2.getCountry());

        addressRepository.deleteAll();
        hotelRepository.deleteAll();
    }

}
