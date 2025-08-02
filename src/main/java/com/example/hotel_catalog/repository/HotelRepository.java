package com.example.hotel_catalog.repository;

import com.example.hotel_catalog.model.Hotel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Map;
import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Query(nativeQuery = true, value = "select brand, count(*) as count from hotel group by brand")
    Map<String, Integer> getBrandHistogram();

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "hotel-with-full-data")
    Optional<Hotel> findById(Long id);

}
