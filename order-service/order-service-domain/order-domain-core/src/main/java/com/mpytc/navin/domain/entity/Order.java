package com.mpytc.navin.domain.entity;

import com.mpytc.navin.domain.exception.OrderDomainException;
import com.mpytc.navin.domain.valueobject.*;

import java.util.List;

public class Order extends AggregateRoot<OrderId>{
    private final CustomerId customerId;
    private final BusinessId businessId;
    private final StressAddress stressAddress;
    private final Money price;
    private final List<OrderItem> items;

    private TrackingId trackingId;
    private OrderStatus orderStatus;
    private List<String> failureMessages;

    private Order(Builder builder) {
        super.setId(builder.id);
        customerId = builder.customerId;
        businessId = builder.businessId;
        stressAddress = builder.stressAddress;
        price = builder.price;
        items = builder.items;
        trackingId = builder.trackingId;
        orderStatus = builder.orderStatus;
        failureMessages = builder.failureMessages;
    }

    //--------business logic
    public void validateOrder(){
        validateInitialOrder();
        validateTotalPrice();
        validateItemsPrice();
    }

    private void validateItemPrice(OrderItem orderItem) {
        if (!orderItem.isPriceValid()) {
            throw new OrderDomainException("Order item price: " + orderItem.getPrice().getAmount() +
                    " is not valid for product: " + orderItem.getProduct().getId().value());
        }
    }

    private void validateItemsPrice() {
        Money orderItemsTotalPrice = items.stream()
                .map(orderItem -> {
                    validateItemPrice(orderItem);
                    return orderItem.getSubTotal();
                })
                .reduce(Money.ZERO, Money::add);

        if (!price.equals(orderItemsTotalPrice)) {
            throw new OrderDomainException("Total price: " + price.getAmount()
                    + " is not equal to order items total price: " + orderItemsTotalPrice.getAmount());
        }
    }

    private void validateTotalPrice() {
        if (price == null || !price.isGreaterThanZero()) {
            throw new OrderDomainException("Total price must be greater than zero");
        }
    }

    private void validateInitialOrder() {
        if (orderStatus != null || super.getId() != null) {
            throw new OrderDomainException("Order is not in correct status for initialization");
        }
    }

    public void initialiezeOrder(){

    }
    public void pay(){

    }
    public void approve(){

    }
    public void initiCancel(){

    }

    public void cancel(){

    }


    public static final class Builder {
        private OrderId id;
        private CustomerId customerId;
        private BusinessId businessId;
        private StressAddress stressAddress;
        private Money price;
        private List<OrderItem> items;
        private TrackingId trackingId;
        private OrderStatus orderStatus;
        private List<String> failureMessages;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(OrderId val) {
            id = val;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder businessId(BusinessId val) {
            businessId = val;
            return this;
        }

        public Builder stressAddress(StressAddress val) {
            stressAddress = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder items(List<OrderItem> val) {
            items = val;
            return this;
        }

        public Builder trackingId(TrackingId val) {
            trackingId = val;
            return this;
        }

        public Builder orderStatus(OrderStatus val) {
            orderStatus = val;
            return this;
        }

        public Builder failureMessages(List<String> val) {
            failureMessages = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
