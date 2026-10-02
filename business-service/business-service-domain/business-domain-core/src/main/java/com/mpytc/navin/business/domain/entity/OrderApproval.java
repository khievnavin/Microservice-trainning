package com.mpytc.navin.business.domain.entity;

import com.mpytc.navin.order.domain.entity.BaseEntity;
import com.mpytc.navin.order.domain.valueobject.BusinessId;
import com.mpytc.navin.order.domain.valueobject.OrderApprovalId;
import com.mpytc.navin.order.domain.valueobject.OrderApprovalStatus;
import com.mpytc.navin.order.domain.valueobject.OrderId;
import lombok.Getter;

@Getter
public class OrderApproval extends BaseEntity<OrderApprovalId> {
    private final BusinessId businessId;
    private final OrderId orderId;
    private final OrderApprovalStatus approvalStatus;

    private OrderApproval(Builder builder) {
        super.setId(builder.id);
        businessId = builder.businessId;
        orderId = builder.orderId;
        approvalStatus = builder.approvalStatus;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private OrderApprovalId id;
        private BusinessId businessId;
        private OrderId orderId;
        private OrderApprovalStatus approvalStatus;

        private Builder() {
        }

        public Builder id(OrderApprovalId val) {
            id = val;
            return this;
        }

        public Builder businessId(BusinessId val) {
            businessId = val;
            return this;
        }

        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        public Builder approvalStatus(OrderApprovalStatus val) {
            approvalStatus = val;
            return this;
        }

        public OrderApproval build() {
            return new OrderApproval(this);
        }
    }
}
