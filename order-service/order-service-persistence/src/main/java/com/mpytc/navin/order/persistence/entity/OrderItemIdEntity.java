package com.mpytc.navin.order.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
public class OrderItemIdEntity implements Serializable {

    private UUID id;

    private OrderEntity order;



}
