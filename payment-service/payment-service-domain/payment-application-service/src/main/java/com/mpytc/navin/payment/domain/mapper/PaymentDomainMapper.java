package com.mpytc.navin.payment.domain.mapper;

import com.mpytc.navin.payment.domain.dto.CreatePaymentCommand;
import com.mpytc.navin.payment.domain.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentDomainMapper {
    @Mapping(source = "orderId", target = "orderId.value")
    @Mapping(source = "customerId", target = "customerId.value")
    @Mapping(source = "price", target = "price.amount")
    Payment createPaymentCommandToPayment(CreatePaymentCommand createPaymentCommand);
}
