package com.example.hotel_catalog.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

import static com.example.hotel_catalog.constant.TableName.ADDRESS;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = ADDRESS)
public class Address {

    @Id
    private Long id;

    private Short houseNumber;

    private String street;

    private String city;

    private String country;

    private String postCode;

    @MapsId
    @JoinColumn(name = "id")
    @OneToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.LAZY)
    private Hotel hotel;

    @EqualsAndHashCode.Exclude
    @ManyToMany
    private Set<Amenity> amenities = new LinkedHashSet<>();

}
