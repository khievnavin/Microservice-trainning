package com.mpytc.navin.order.persistence.entity;

import com.mpytc.navin.order.domain.valueobject.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

//JPA Enity must be POJO (Plain Old Java Object) class
//POLO: getter,setter,noArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity //Create Table
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private UUID customerId;

    private UUID businessId;

    private BigDecimal price;

    @OneToMany(mappedBy = "order")
    private List<OrderAddressEntity> items;

    @OneToOne
    private OrderItemEntity orderAddress;

    private UUID trackingId;

    private OrderStatus orderStatus;

    private String failureMessages; //message1,message2


}
