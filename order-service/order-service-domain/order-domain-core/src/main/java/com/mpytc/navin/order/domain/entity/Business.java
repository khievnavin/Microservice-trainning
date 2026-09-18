package com.mpytc.navin.order.domain.entity;

import com.mpytc.navin.order.domain.valueobject.BusinessId;

import java.util.List;

public class Business extends AggregateRoot<BusinessId> {

    private final List<Product> products;
    private final boolean active;

    public boolean isActive() {
        return active;
    }

    public List<Product> getProducts() {
        return products;
    }

    private Business(Builder builder) {
       super.setId(builder.id);
        products = builder.products;
        active = builder.isactive;
    }

    public static final class Builder {
        private BusinessId id;
        private List<Product> products;
        private boolean isactive;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(BusinessId val) {
            id = val;
            return this;
        }

        public Builder products(List<Product> val) {
            products = val;
            return this;
        }

        public Builder isactive(boolean val) {
            isactive = val;
            return this;
        }

        public Business build() {
            return new Business(this);
        }
    }
}
