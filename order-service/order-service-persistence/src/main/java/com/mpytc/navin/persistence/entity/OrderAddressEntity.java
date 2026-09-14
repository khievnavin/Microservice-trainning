package com.mpytc.navin.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order_addresses")
public class OrderAddressEntity {

    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID id; //changed from Int to UUID

    private UUID productId;
    private String productName;
    private BigDecimal productPrice;

    private  Integer quantity;
    private BigDecimal price;
    private BigDecimal subTotal;

    @ManyToOne
    private OrderEntity order;


}
