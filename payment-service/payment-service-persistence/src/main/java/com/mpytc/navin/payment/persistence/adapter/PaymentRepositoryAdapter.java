package com.mpytc.navin.payment.persistence.adapter;

import com.mpytc.navin.payment.domain.entity.Payment;
import com.mpytc.navin.payment.domain.port.output.PaymentRepository;
import com.mpytc.navin.payment.persistence.entity.PaymentEntity;
import com.mpytc.navin.payment.persistence.mapper.PaymentPersistenceMapper;
import com.mpytc.navin.payment.persistence.repository.PaymentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryAdapter implements PaymentRepository {
    private final PaymentJpaRepository paymentJpaRepository;
    private final PaymentPersistenceMapper paymentPersistenceMapper;

    @Override
    public Payment savePayment(Payment payment) {
        PaymentEntity paymentEntity = paymentPersistenceMapper.paymentToPaymentEntity(payment);
        PaymentEntity savedPaymentEntity = paymentJpaRepository.save(paymentEntity);
        return paymentPersistenceMapper.paymentEntityToPayment(savedPaymentEntity);
    }
}
