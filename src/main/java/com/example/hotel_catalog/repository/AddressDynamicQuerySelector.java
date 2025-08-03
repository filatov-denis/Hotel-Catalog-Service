package com.example.hotel_catalog.repository;

import com.example.hotel_catalog.filter.HotelParameterizedFilter;
import com.example.hotel_catalog.model.Amenity;
import com.example.hotel_catalog.model.Hotel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.hotel_catalog.constant.FieldName.*;

@Component
public class AddressDynamicQuerySelector {

    private final EntityManager entityManager;

    private final CriteriaBuilder criteriaBuilder;

    public AddressDynamicQuerySelector(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public List<Hotel> getByDynamicFilter(HotelParameterizedFilter filter) {
        CriteriaQuery<Hotel> cq = criteriaBuilder.createQuery(Hotel.class);
        Root<Hotel> hotelRoot = cq.from(Hotel.class);

        List<Predicate> predicates = createPredicates(hotelRoot, filter);

        cq.select(hotelRoot);
        cq.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));

        return entityManager.createQuery(cq)
                .setHint("jakarta.persistence.fetchgraph", entityManager.getEntityGraph("hotel-with-address"))
                .getResultList();
    }

    private <T> List<Predicate> createPredicates(Root<T> root, HotelParameterizedFilter filter) {
        List<Predicate> predicates = new ArrayList<>();

        if(Objects.nonNull(filter.getName())) {
            predicates.add(criteriaBuilder.like(
                    root.get(NAME), formLikeSentence(filter.getName()))
            );
        }

        if(Objects.nonNull(filter.getBrand())) {
            predicates.add(criteriaBuilder.like(
                    root.get(BRAND), formLikeSentence(filter.getBrand()))
            );
        }

        if(Objects.nonNull(filter.getCity())) {
            root.join(ADDRESS, JoinType.LEFT);
            predicates.add(criteriaBuilder.like(
                    root.get(ADDRESS).get(CITY), formLikeSentence(filter.getCity()))
            );
        }

        if(Objects.nonNull(filter.getCountry())) {
            root.join(ADDRESS, JoinType.LEFT);
            predicates.add(criteriaBuilder.like(
                    root.get(ADDRESS).get(COUNTRY), formLikeSentence(filter.getCountry()))
            );
        }

        if(Objects.nonNull(filter.getAmenities())) {
            Join<Hotel, Amenity> join = root.join(AMENITIES, JoinType.INNER);
            predicates.add(criteriaBuilder.equal(join.get(NAME), filter.getAmenities()));
        }

        return predicates;
    }

    private String formLikeSentence(String data) {return "%" + data + "%";}

}