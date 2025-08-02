package com.example.hotel_catalog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

import static com.example.hotel_catalog.constant.SequenceName.*;
import static com.example.hotel_catalog.constant.TableName.AMENITY;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = AMENITY)
public class Amenity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = AMENITY_GENERATOR)
    @SequenceGenerator(name = AMENITY_GENERATOR, sequenceName = AMENITY_SEQUENCE, allocationSize = 20)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "amenities", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    private Set<Hotel> hotels = new LinkedHashSet<>();

}
