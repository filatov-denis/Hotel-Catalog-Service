package com.example.hotel_catalog.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Set;

import static com.example.hotel_catalog.constant.SequenceName.HOTEL_GENERATOR;
import static com.example.hotel_catalog.constant.SequenceName.HOTEL_SEQUENCE;
import static com.example.hotel_catalog.constant.TableName.HOTEL;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = HOTEL)
@NamedEntityGraph(name = "hotel-with-full-data", attributeNodes = {@NamedAttributeNode(value = "address"), @NamedAttributeNode(value = "amenities")})
@NamedEntityGraph(name = "hotel-with-address", attributeNodes = @NamedAttributeNode(value = "address"))
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = HOTEL_GENERATOR)
    @SequenceGenerator(name = HOTEL_GENERATOR, sequenceName = HOTEL_SEQUENCE)
    private Long id;

    private String name;

    private String brand;

    private String description;

    private String phone;

    private String email;

    private LocalTime checkIn;

    private LocalTime checkOut;

    @PrimaryKeyJoinColumn
    @EqualsAndHashCode.Exclude
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.LAZY)
    private Address address;

    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "hotel_amenity",
            joinColumns = @JoinColumn(name = "hotel_id"),
            inverseJoinColumns = @JoinColumn(name = "amenity_id"))
    private Set<Amenity> amenities = new LinkedHashSet<>();

    public void addAmenity(Amenity amenity) {
        amenities.add(amenity);
        amenity.getHotels().add(this);
    }

}
