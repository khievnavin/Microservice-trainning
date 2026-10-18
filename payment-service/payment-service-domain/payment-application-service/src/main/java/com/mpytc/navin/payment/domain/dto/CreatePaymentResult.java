package com.mpytc.navin.payment.domain.dto;

import com.mpytc.navin.order.domain.valueobject.PaymentStatus;

import java.util.UUID;

public record CreatePaymentResult(
        UUID paymentId,
        PaymentStatus paymentStatus
) {
}
