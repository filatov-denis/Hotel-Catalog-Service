package com.example.hotel_catalog.repository;

import com.example.hotel_catalog.filter.HotelParameterizedFilter;
import com.example.hotel_catalog.model.Hotel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressDynamicQuerySelector {

    private final EntityManager entityManager;

    private final CriteriaBuilder criteriaBuilder;

    public AddressDynamicQuerySelector(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public List<Hotel> getByDynamicFilter(HotelParameterizedFilter filter) {
        //todo дописать
        return null;
    }

}


