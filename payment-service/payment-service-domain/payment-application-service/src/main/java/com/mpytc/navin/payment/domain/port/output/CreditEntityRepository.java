package com.mpytc.navin.payment.domain.port.output;

import com.mpytc.navin.order.domain.valueobject.CustomerId;
import com.mpytc.navin.payment.domain.entity.CreditEntry;

public interface CreditEntityRepository {
    CreditEntry findByCustomerId(CustomerId customerId);

    CreditEntry save(CreditEntry creditEntry);
}
