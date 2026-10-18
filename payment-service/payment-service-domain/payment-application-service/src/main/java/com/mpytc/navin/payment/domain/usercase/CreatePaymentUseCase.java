package com.mpytc.navin.payment.domain.usercase;

import com.mpytc.navin.payment.domain.dto.CreatePaymentCommand;
import com.mpytc.navin.payment.domain.dto.CreatePaymentResult;
import com.mpytc.navin.payment.domain.mapper.PaymentDomainMapper;
import com.mpytc.navin.payment.domain.port.output.CreditEntityRepository;
import com.mpytc.navin.payment.domain.port.output.CreditHistoryRepository;
import com.mpytc.navin.payment.domain.port.output.PaymentRepository;
import com.mpytc.navin.payment.domain.entity.CreditEntry;
import com.mpytc.navin.payment.domain.entity.CreditHistory;
import com.mpytc.navin.payment.domain.entity.Payment;
import com.mpytc.navin.payment.domain.exception.PaymentDomainException;
import com.mpytc.navin.payment.domain.service.PaymentDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreatePaymentUseCase {
    private final PaymentDomainService paymentDomainService;
    private final PaymentRepository paymentRepository;
    private final PaymentDomainMapper paymentDomainMapper;
    private final CreditEntityRepository creditEntityRepository;
    private final CreditHistoryRepository creditHistoryRepository;

    public CreatePaymentResult execute(CreatePaymentCommand createPaymentCommand) {
        log.info("executing CreatePaymentUseCase: {}", createPaymentCommand);

        //1. convert input object by map-struct
        Payment payment = paymentDomainMapper.createPaymentCommandToPayment(createPaymentCommand);

        //2. load customer credit
        CreditEntry creditEntry = creditEntityRepository.findByCustomerId(payment.getCustomerId());
        if (creditEntry == null) {
            throw new PaymentDomainException("Could not find credit entry for customer: "
                    + payment.getCustomerId().value());
        }

        //3. domain logic (validate → initialize → subtract credit → COMPLETED)
        CreditHistory creditHistory = paymentDomainService.validateAndInitiatePayment(payment, creditEntry);

        //4. save
        Payment savePayment = paymentRepository.savePayment(payment);
        if(savePayment == null){
            throw  new PaymentDomainException("Could not save payment into Database");
        }
        creditEntityRepository.save(creditEntry);
        creditHistoryRepository.save(creditHistory);

        log.info("Payment {} completed for customer {}", savePayment.getId().value(),
                payment.getCustomerId().value());

        return new CreatePaymentResult(savePayment.getId().value(), savePayment.getPaymentStatus());
    }
}
