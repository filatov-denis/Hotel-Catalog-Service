package com.example.hotel_catalog.repository;

import com.example.hotel_catalog.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
