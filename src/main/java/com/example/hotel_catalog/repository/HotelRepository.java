package com.example.hotel_catalog.repository;

import com.example.hotel_catalog.dto.HistogramResult;
import com.example.hotel_catalog.model.Hotel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Query(nativeQuery = true, value = "select brand, count(*) as count from hotel group by brand")
    List<HistogramResult> getBrandHistogram();

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "hotel-with-full-data")
    Optional<Hotel> findById(Long id);

}
