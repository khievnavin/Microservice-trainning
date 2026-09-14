package com.mpytc.navin.domain.entity;

import com.mpytc.navin.domain.valueobject.CustomerId;

public class Customer extends AggregateRoot<CustomerId>{
    private final String username;
    private final String familyname;
    private final String givename;

    private Customer(Builder builder) {
        super.setId(builder.id);
        username = builder.username;
        familyname = builder.familyname;
        givename = builder.givename;
    }

    public static final class Builder {
        private CustomerId id;
        private String username;
        private String familyname;
        private String givename;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(CustomerId val) {
            id = val;
            return this;
        }

        public Builder username(String val) {
            username = val;
            return this;
        }

        public Builder familyname(String val) {
            familyname = val;
            return this;
        }

        public Builder givename(String val) {
            givename = val;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}
