package com.mpytc.navin.payment.domain.service;

import com.mpytc.navin.order.domain.valueobject.PaymentStatus;
import com.mpytc.navin.payment.domain.entity.CreditEntry;
import com.mpytc.navin.payment.domain.entity.CreditHistory;
import com.mpytc.navin.payment.domain.entity.Payment;

public interface PaymentDomainService {
    CreditHistory validateAndInitiatePayment(Payment payment, CreditEntry creditEntry);

    void updatePaymentStatus(Payment payment, PaymentStatus newPaymentStatus);
}
