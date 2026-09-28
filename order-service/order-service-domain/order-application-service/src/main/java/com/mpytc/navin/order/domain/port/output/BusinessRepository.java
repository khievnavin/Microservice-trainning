package com.mpytc.navin.order.domain.port.output;

import com.mpytc.navin.order.domain.entity.Business;

import java.util.Optional;

public interface BusinessRepository {

    Optional<Business> findBusiness(Business business);
}
