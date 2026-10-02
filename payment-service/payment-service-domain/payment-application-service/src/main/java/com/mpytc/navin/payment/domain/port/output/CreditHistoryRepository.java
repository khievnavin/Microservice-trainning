package com.mpytc.navin.payment.domain.port.output;

import com.mpytc.navin.payment.domain.entity.CreditHistory;

public interface CreditHistoryRepository {
    CreditHistory save(CreditHistory creditHistory);
}
