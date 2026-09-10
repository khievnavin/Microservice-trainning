package com.mpytc.navin.persistence.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "produts")
public class ProductEnity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private BigDecimal price;

    @OneToOne(mappedBy = "product")
    private OrderItemEnity orderItem;
}
