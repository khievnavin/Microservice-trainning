package com.mpytc.navin.payment.domain.event;

import com.mpytc.navin.order.domain.event.DomainEvent;
import com.mpytc.navin.payment.domain.entity.Payment;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
public class PaymentEvent implements DomainEvent<Payment> {
    private final Payment payment;
    private final ZonedDateTime createdAt;
    private final List<String> failureMessages ;

    public PaymentEvent(Payment payment, ZonedDateTime createdAt, List<String> failureMessages) {
        this.payment = payment;
        this.createdAt = createdAt;
        this.failureMessages = failureMessages;
    }

}
