package com.example.hotel_catalog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.LinkedHashSet;
import java.util.Set;

import static com.example.hotel_catalog.constant.TableName.AMENITY;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = AMENITY)
public class Amenity {

    @Id
    private Long id;

    private String name;

    @ManyToMany
    @EqualsAndHashCode.Exclude
    private Set<Hotel> hotels = new LinkedHashSet<>();

}
