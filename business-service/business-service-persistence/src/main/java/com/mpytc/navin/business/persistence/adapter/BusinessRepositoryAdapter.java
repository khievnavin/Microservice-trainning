package com.mpytc.navin.business.persistence.adapter;

import com.mpytc.navin.business.domain.entity.Business;
import com.mpytc.navin.business.persistence.entity.BusinessEntity;
import com.mpytc.navin.business.persistence.mapper.BusinessPersistenceMapper;
import com.mpytc.navin.business.persistence.repository.BusinessJpaRepository;
import com.mpytc.navin.business.port.output.BusinessRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class BusinessRepositoryAdapter implements BusinessRepository {

    private final BusinessJpaRepository businessJpaRepository;
    private final BusinessPersistenceMapper businessPersistenceMapper;

    @Override
    public Optional<Business> findBusinessInformation(Business business) {
        List<UUID> businessProducts = businessPersistenceMapper.businessToBusinessProducts(business);

        List<BusinessEntity> businessEntities = businessJpaRepository.findByBusinessIdAndProductIdIn(
                business.getId().value(),
                businessProducts
        );

        return Optional.of(businessPersistenceMapper.businessEntityToBusiness(businessEntities));
    }
}
