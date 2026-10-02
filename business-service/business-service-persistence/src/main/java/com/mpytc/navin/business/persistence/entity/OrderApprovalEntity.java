package com.mpytc.navin.business.persistence.entity;

import com.mpytc.navin.order.domain.valueobject.OrderApprovalStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order_approvals")
public class OrderApprovalEntity {
    @Id
    private UUID id;

    private UUID businessId;
    private UUID orderId;

    @Enumerated(EnumType.STRING)
    private OrderApprovalStatus status;
}
