package com.mpytc.navin.payment.persistence.adapter;

import com.mpytc.navin.order.domain.valueobject.CustomerId;
import com.mpytc.navin.payment.domain.entity.CreditEntry;
import com.mpytc.navin.payment.domain.port.output.CreditEntityRepository;
import com.mpytc.navin.payment.persistence.entity.CreditEntryEntity;
import com.mpytc.navin.payment.persistence.mapper.CreditEntryPersistenceMapper;
import com.mpytc.navin.payment.persistence.repository.CreditEntryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CreditEntryRepositoryAdapter implements CreditEntityRepository {
    private final CreditEntryJpaRepository creditEntryJpaRepository;
    private final CreditEntryPersistenceMapper creditEntryPersistenceMapper;

    @Override
    public CreditEntry findByCustomerId(CustomerId customerId) {
        return creditEntryJpaRepository.findByCustomerId(customerId.value())
                .map(creditEntryPersistenceMapper::creditEntryEntityToCreditEntry)
                .orElse(null);
    }

    @Override
    public CreditEntry save(CreditEntry creditEntry) {
        CreditEntryEntity creditEntryEntity = creditEntryPersistenceMapper.creditEntryToCreditEntryEntity(creditEntry);
        CreditEntryEntity savedCreditEntryEntity = creditEntryJpaRepository.save(creditEntryEntity);
        return creditEntryPersistenceMapper.creditEntryEntityToCreditEntry(savedCreditEntryEntity);
    }
}
