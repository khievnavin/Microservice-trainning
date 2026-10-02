package com.mpytc.navin.business.port.output;

import com.mpytc.navin.business.domain.entity.OrderApproval;

public interface OrderApprovalRepository {

    OrderApproval save(OrderApproval orderApproval);

}