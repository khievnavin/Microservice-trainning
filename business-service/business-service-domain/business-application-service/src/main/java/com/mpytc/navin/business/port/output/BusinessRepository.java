package com.mpytc.navin.business.port.output;

import com.mpytc.navin.business.domain.entity.Business;

import java.util.Optional;

public interface BusinessRepository {

    Optional<Business> findBusinessInformation(Business business);

}
