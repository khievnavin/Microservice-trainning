package com.mpytc.navin.order.domain.port.output;

import com.mpytc.navin.order.domain.entity.Business;

import java.util.Optional;
import java.util.UUID;

public interface BusinessRepository {

    Optional<Business> findBusiness(UUID businessId);
}
