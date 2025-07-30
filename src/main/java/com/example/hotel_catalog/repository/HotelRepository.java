package com.example.hotel_catalog.repository;

import com.example.hotel_catalog.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
}
