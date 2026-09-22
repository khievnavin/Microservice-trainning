package com.mpytc.navin.order.persistence.adapter;

import com.mpytc.navin.order.domain.entity.Business;
import com.mpytc.navin.order.domain.port.output.BusinessRepository;
import com.mpytc.navin.order.persistence.entity.BusinessEntity;
import com.mpytc.navin.order.persistence.mapper.BusinessPersistenceMapper;
import com.mpytc.navin.order.persistence.repository.BusinessJpaRepository;
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
    public Optional<Business> findBusiness(Business business) {

        List<UUID> businessProducts = businessPersistenceMapper.businessToBusinessProducts(business);

        List<BusinessEntity> businessEntities = businessJpaRepository.findByBusinessIdAndProductIdIn
                (business.getId().value(),
                        businessProducts);

        if (businessEntities.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(businessPersistenceMapper.businessEntityToBusiness(businessEntities));
    }

}
