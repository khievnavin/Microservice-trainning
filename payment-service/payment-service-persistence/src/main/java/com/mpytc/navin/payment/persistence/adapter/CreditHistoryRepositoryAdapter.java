package com.mpytc.navin.payment.persistence.adapter;

import com.mpytc.navin.payment.domain.entity.CreditHistory;
import com.mpytc.navin.payment.domain.port.output.CreditHistoryRepository;
import com.mpytc.navin.payment.persistence.entity.CreditHistoryEntity;
import com.mpytc.navin.payment.persistence.mapper.CreditHistoryPersistenceMapper;
import com.mpytc.navin.payment.persistence.repository.CreditHistoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CreditHistoryRepositoryAdapter implements CreditHistoryRepository {
    private final CreditHistoryJpaRepository creditHistoryJpaRepository;
    private final CreditHistoryPersistenceMapper creditHistoryPersistenceMapper;

    @Override
    public CreditHistory save(CreditHistory creditHistory) {
        CreditHistoryEntity creditHistoryEntity =
                creditHistoryPersistenceMapper.creditHistoryToCreditHistoryEntity(creditHistory);
        CreditHistoryEntity savedCreditHistoryEntity = creditHistoryJpaRepository.save(creditHistoryEntity);
        return creditHistoryPersistenceMapper.creditHistoryEntityToCreditHistory(savedCreditHistoryEntity);
    }
}
