package com.mpytc.navin.domain.entity;

import com.mpytc.navin.domain.valueobject.Money;
import com.mpytc.navin.domain.valueobject.ProductId;

public class Product extends BaseEntity<ProductId> {
    private final String name;
    private final Money price;

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

    private Product(Builder builder) {
        super.setId(builder.id);
        name = builder.name;
        price = builder.price;
    }

    public static final class Builder {
        public Money price;
        private ProductId id;
        private String name;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(ProductId val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
