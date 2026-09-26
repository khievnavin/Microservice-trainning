package com.mpytc.navin.order.persistence.entity;

import com.mpytc.navin.order.domain.valueobject.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

//JPA Entity must be POJO (Plain Old Java Object) class
//POLO: getter, setter, noArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Entity //Create Table
@Table(name = "orders")
public class OrderEntity {
    @Id
    private UUID id;

    private UUID customerId;

    private UUID businessId;

    private BigDecimal price;
    //add cascade
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItemEntity> items;

    @OneToOne(cascade = CascadeType.ALL)
    private OrderAddressEntity orderAddress;

    private UUID trackingId;

    //add enumerated
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private String failureMessages; // message1;message2

}
