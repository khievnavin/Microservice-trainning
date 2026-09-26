package com.mpytc.navin.order.domain.entity;

import com.mpytc.navin.order.domain.valueobject.Money;
import com.mpytc.navin.order.domain.valueobject.ProductId;
import lombok.Getter;

@Getter
public class Product extends BaseEntity<ProductId> {
    private  String name;
    private  Money price;

    private Product(Builder builder) {
        super.setId(builder.id);
        name = builder.name;
        price = builder.price;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void updateConfirmNameAndPrice(String name, Money price){
        this.name = name;
        this.price = price;
    }


    public static final class Builder {
        private ProductId id;
        private String name;
        private Money price;

        private Builder() {
        }

        public Builder id(ProductId val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
