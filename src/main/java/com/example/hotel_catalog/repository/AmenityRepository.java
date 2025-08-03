package com.example.hotel_catalog.repository;

import com.example.hotel_catalog.dto.HistogramResult;
import com.example.hotel_catalog.model.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AmenityRepository extends JpaRepository<Amenity, Long> {

    @Query(nativeQuery = true, value = "select a.name, count(*) as count from amenity as a " +
            "join hotel_amenity as ha on a.id = ha.amenity_id group by a.name")
    List<HistogramResult> getAmenityHistogram();

}
