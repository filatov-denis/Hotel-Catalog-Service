package com.example.hotel_catalog.repository;

import com.example.hotel_catalog.model.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;

public interface AmenityRepository extends JpaRepository<Amenity, Long> {

    @Query(nativeQuery = true, value = "select a.name, count(*) as count from amenity as a " +
            "right join hotel_amenity as ha on a.id = ha.hotel_id group by a.name")
    Map<String, Integer> getAmenityHistogram();

}
