package com.mpytc.navin.business.domain.entity;

import com.mpytc.navin.order.domain.entity.BaseEntity;
import com.mpytc.navin.order.domain.valueobject.Money;
import com.mpytc.navin.order.domain.valueobject.OrderId;
import com.mpytc.navin.order.domain.valueobject.OrderStatus;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderDetail extends BaseEntity<OrderId> {
    private final OrderStatus orderStatus;
    private final Money totalAmount;
    private final List<Product> products;

    private OrderDetail(Builder builder) {
        super.setId(builder.id);
        orderStatus = builder.orderStatus;
        totalAmount = builder.totalAmount;
        products = builder.products;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private OrderId id;
        private OrderStatus orderStatus;
        private Money totalAmount;
        private List<Product> products;

        private Builder() {
        }

        public Builder id(OrderId val) {
            id = val;
            return this;
        }

        public Builder orderStatus(OrderStatus val) {
            orderStatus = val;
            return this;
        }

        public Builder totalAmount(Money val) {
            totalAmount = val;
            return this;
        }

        public Builder products(List<Product> val) {
            products = val;
            return this;
        }

        public OrderDetail build() {
            return new OrderDetail(this);
        }
    }
}
