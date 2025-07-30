package com.example.hotel_catalog.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

import static com.example.hotel_catalog.constant.SequenceNames.HOTEL_GENERATOR;
import static com.example.hotel_catalog.constant.SequenceNames.HOTEL_SEQUENCE;
import static com.example.hotel_catalog.constant.TableName.HOTEL;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = HOTEL)
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = HOTEL_GENERATOR)
    @SequenceGenerator(name = HOTEL_GENERATOR, sequenceName = HOTEL_SEQUENCE)
    private Long id;

    private String name;

    private String brand;

    private String phoneNumber;

    private String email;

    private LocalTime checkIn;

    private LocalTime checkOut;

    @PrimaryKeyJoinColumn
    @EqualsAndHashCode.Exclude
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH}, fetch = FetchType.LAZY)
    private Address address;

}
