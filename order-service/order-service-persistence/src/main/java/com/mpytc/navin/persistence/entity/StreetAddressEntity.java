package com.mpytc.navin.persistence.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "order_address")
public class StreetAddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String streeName;
    private String postalCode;
    private String city;

    @OneToOne(mappedBy = "streetAddress")
    private OrderEntity order;

}