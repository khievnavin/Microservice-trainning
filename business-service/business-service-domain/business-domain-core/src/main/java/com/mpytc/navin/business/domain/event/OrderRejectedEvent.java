package com.mpytc.navin.business.domain.event;

import com.mpytc.navin.business.domain.entity.OrderApproval;
import com.mpytc.navin.order.domain.valueobject.BusinessId;

import java.time.ZonedDateTime;
import java.util.List;

public class OrderRejectedEvent extends OrderApprovalEvent {
    public OrderRejectedEvent(OrderApproval orderApproval, BusinessId businessId, List<String> failureMessages, ZonedDateTime createdAt) {
        super(orderApproval, businessId, failureMessages, createdAt);
    }
}
