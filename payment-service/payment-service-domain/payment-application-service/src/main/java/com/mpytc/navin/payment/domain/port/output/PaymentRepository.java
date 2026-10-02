package com.mpytc.navin.payment.domain.port.output;

import com.mpytc.navin.payment.domain.entity.Payment;

public interface PaymentRepository {
    Payment savePayment(Payment payment);
}
