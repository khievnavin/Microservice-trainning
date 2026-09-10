package com.mpytc.navin.persistence.entity;

import com.mpytc.navin.domain.valueobject.OrderStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity //Create Table
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID orderId;
    private UUID customerId;
    private BigDecimal price;

    @OneToMany(mappedBy = "order")
    private List<OrderItemEnity> items;

    @OneToOne
    private StreetAddressEntity streetAddress;

    private UUID trackingId;

    private OrderStatus orderStatus;

    private String failureMessages; //message1,message2


}
