package com.mpytc.navin.domain.dto;

import com.mpytc.navin.domain.valueobject.BusinessId;
import com.mpytc.navin.domain.valueobject.CustomerId;
import com.mpytc.navin.domain.valueobject.Money;
import com.mpytc.navin.domain.valueobject.StressAddress;

public record CreateOrderRequest(
        CustomerId customerId,
        BusinessId businessId,
        StressAddress deliveryAddress,
        Money price)
{




}
