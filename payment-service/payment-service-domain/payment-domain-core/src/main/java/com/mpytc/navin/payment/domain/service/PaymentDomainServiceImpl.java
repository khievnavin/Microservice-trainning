package com.mpytc.navin.payment.domain.service;

import com.mpytc.navin.order.domain.valueobject.CreditHistoryId;
import com.mpytc.navin.order.domain.valueobject.PaymentStatus;
import com.mpytc.navin.order.domain.valueobject.TransactionType;
import com.mpytc.navin.payment.domain.entity.CreditEntry;
import com.mpytc.navin.payment.domain.entity.CreditHistory;
import com.mpytc.navin.payment.domain.entity.Payment;

import java.util.UUID;

public class PaymentDomainServiceImpl implements PaymentDomainService {
    @Override
    public CreditHistory validateAndInitiatePayment(Payment payment, CreditEntry creditEntry) {
        // 1. Payment logic
        payment.validatePayment();
        payment.initializePayment();

        // 2. CreditEntry logic → ដកលុយពី credit របស់ customer
        creditEntry.subtractCreditAmount(payment.getPrice());

        // 3. Payment success
        payment.updateStatus(PaymentStatus.COMPLETED);

        // 4. CreditHistory → កត់ត្រាថាបានដកលុយ (DEBIT)
        return CreditHistory.builder()
                .id(new CreditHistoryId(UUID.randomUUID()))
                .customerId(payment.getCustomerId())
                .amount(payment.getPrice())
                .transactionType(TransactionType.DEBIT)
                .build();
    }

    @Override
    public void updatePaymentStatus(Payment payment, PaymentStatus newPaymentStatus) {
        payment.updateStatus(newPaymentStatus);
    }
}