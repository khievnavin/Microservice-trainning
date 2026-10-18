package com.mpytc.navin.business.domain.event;

import com.mpytc.navin.order.domain.event.DomainEvent;
import com.mpytc.navin.business.domain.entity.OrderApproval;
import com.mpytc.navin.order.domain.valueobject.BusinessId;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
public abstract class OrderApprovalEvent implements DomainEvent<OrderApproval> {
    private final OrderApproval orderApproval;
    private final BusinessId businessId;
    private final List<String> failureMessages;
    private final ZonedDateTime createdAt;

    public OrderApprovalEvent(OrderApproval orderApproval, BusinessId businessId, List<String> failureMessages, ZonedDateTime createdAt) {
        this.orderApproval = orderApproval;
        this.businessId = businessId;
        this.failureMessages = failureMessages;
        this.createdAt = createdAt;
    }

}
