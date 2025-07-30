package com.example.hotel_catalog.repository;

import com.example.hotel_catalog.model.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmenityRepository extends JpaRepository<Amenity, Long> {

}
