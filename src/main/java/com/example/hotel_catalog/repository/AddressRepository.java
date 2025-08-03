package com.example.hotel_catalog.repository;

import com.example.hotel_catalog.dto.HistogramResult;
import com.example.hotel_catalog.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query(nativeQuery = true, value = "select :data, count(*) as count from address group by :data")
    List<HistogramResult> getDataHistogram(String data);

}
