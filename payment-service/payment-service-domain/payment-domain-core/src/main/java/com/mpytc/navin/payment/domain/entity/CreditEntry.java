package com.mpytc.navin.payment.domain.entity;

import com.mpytc.navin.order.domain.entity.BaseEntity;
import com.mpytc.navin.order.domain.valueobject.CreditEntryId;
import com.mpytc.navin.order.domain.valueobject.CustomerId;
import com.mpytc.navin.order.domain.valueobject.Money;
import com.mpytc.navin.payment.domain.exception.PaymentDomainException;
import lombok.Getter;

@Getter
public class CreditEntry extends BaseEntity<CreditEntryId> {

    private final CustomerId customerId;
    private Money totalCreditAmount;

    // បន្ថែមលុយចូល credit
    public void addCreditAmount(Money amount) {
        if (amount == null || !amount.isGreaterThanZero()) {
            throw new PaymentDomainException("Credit amount to add must be greater than zero");
        }
        totalCreditAmount = totalCreditAmount.add(amount);
    }

    // ដកលុយចេញពី credit
    public void subtractCreditAmount(Money amount) {
        if (amount == null || !amount.isGreaterThanZero()) {
            throw new PaymentDomainException("Credit amount to subtract must be greater than zero");
        }
        // credit មិនគ្រប់ → មិនអនុញ្ញាតឱ្យដក
        if (amount.isGreaterThan(totalCreditAmount)) {
            throw new PaymentDomainException("Customer does not have enough credit. Credit: "
                    + totalCreditAmount.getAmount() + ", price: " + amount.getAmount());
        }
        totalCreditAmount = totalCreditAmount.subtract(amount);
    }

    private CreditEntry(Builder builder) {
        super.setId(builder.id);
        customerId = builder.customerId;
        totalCreditAmount = builder.totalCreditAmount;
    }


    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private CreditEntryId id;
        private CustomerId customerId;
        private Money totalCreditAmount;

        private Builder() {
        }


        public Builder id(CreditEntryId val) {
            id = val;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder totalCreditAmount(Money val) {
            totalCreditAmount = val;
            return this;
        }

        public CreditEntry build() {
            return new CreditEntry(this);
        }
    }
}
