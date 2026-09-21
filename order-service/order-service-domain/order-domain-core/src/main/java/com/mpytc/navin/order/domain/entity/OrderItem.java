package com.mpytc.navin.order.domain.entity;

import com.mpytc.navin.order.domain.valueobject.Money;
import com.mpytc.navin.order.domain.valueobject.OrderId;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItem extends BaseEntity<OrderItem> {

    private final Product product;
    private final Integer quantity;
    private final Money price;
    private final Money subTotal;
    private final OrderId orderId;

    private OrderItem(Builder builder) {
        super.setId(builder.id);
        product = builder.product;
        quantity = builder.quantity;
        price = builder.price;
        subTotal = builder.subTotal;
        orderId = builder.orderId;
    }


    public boolean isPriceValid() {
        return price.isGreaterThanZero() &&
                price.equals(product.getPrice()) &&
                price.multiply(quantity).equals(subTotal);
    }


    public static final class Builder {
        private OrderItem id;
        private Product product;
        private Integer quantity;
        private Money price;
        private Money subTotal;
        private OrderId orderId;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(OrderItem val) {
            id = val;
            return this;
        }

        public Builder product(Product val) {
            product = val;
            return this;
        }

        public Builder quantity(Integer val) {
            quantity = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder subTotal(Money val) {
            subTotal = val;
            return this;
        }

        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}